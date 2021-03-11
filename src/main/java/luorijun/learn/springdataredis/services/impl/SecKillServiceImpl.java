package luorijun.learn.springdataredis.services.impl;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import luorijun.learn.springdataredis.entities.ShopGoods;
import luorijun.learn.springdataredis.entities.ShopOrder;
import luorijun.learn.springdataredis.events.OrderEvent;
import luorijun.learn.springdataredis.repositories.GoodsRepository;
import luorijun.learn.springdataredis.repositories.OrderRepository;
import luorijun.learn.springdataredis.services.SecKillService;

@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService {

    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;

    private final StringRedisTemplate redis;

    private final ApplicationEventPublisher publisher;

    public SecKillServiceImpl(
            GoodsRepository goodsRepository,
            OrderRepository orderRepository,
            StringRedisTemplate redis,
            ApplicationEventPublisher publisher) {

        this.goodsRepository = goodsRepository;
        this.orderRepository = orderRepository;
        this.redis = redis;
        this.publisher = publisher;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void warmup(ApplicationStartedEvent event) {
        var goodsList = goodsRepository.findAll();
        for (var goods : goodsList) {
            var key = String.valueOf(goods.getId());
            var value = String.valueOf(goods.getStock());
            redis.boundValueOps(key).set(value);
        }
    }

    @Transactional
    @Override
    public int secKillWithPessimisticLock(int id) {

        // 检查商品
        var goodsTest = goodsRepository.findByIdWithWriteLock(id);
        if (goodsTest.isEmpty()) {
            throw new RuntimeException("商品不存在");
        }

        // 检查库存
        var goods = goodsTest.get();
        if (goods.getStock() <= 0) {
            throw new RuntimeException("商品库存不足");
        }

        // 返回订单编号
        return handleOrder(goods);
    }

    @Override
    public void secKillWithCacheAndAsyncOrder(int id) {

        var stock = redis.boundValueOps(String.valueOf(id)).decrement();
        if (stock == null) throw new RuntimeException("意外错误");

        if (stock < 0) {
            throw new RuntimeException("商品库存不足");
        }

        publisher.publishEvent(new OrderEvent(id));
    }

    @EventListener(OrderEvent.class)
    public void onOrder(OrderEvent event) {

        var id = (int) event.getSource();
        var goodsTest = goodsRepository.findById(id);
        if (goodsTest.isEmpty()) {
            throw new RuntimeException("商品已下架");
        }

        handleOrder(goodsTest.get());
    }

    private int handleOrder(ShopGoods goods) {

        // 减少库存
        goods.setStock(goods.getStock() - 1);
        goodsRepository.save(goods);

        // 创建订单
        var order = new ShopOrder();
        order.setGoods(goods);
        order.setCount(1);
        order.setTotal(goods.getPrice());
        orderRepository.save(order);

        return order.getId();
    }
}
