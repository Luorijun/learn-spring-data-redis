package luorijun.learn.springdataredis.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import luorijun.learn.springdataredis.entities.ShopOrder;
import luorijun.learn.springdataredis.repositories.GoodsRepository;
import luorijun.learn.springdataredis.repositories.OrderRepository;
import luorijun.learn.springdataredis.services.SecKillService;

@Service
public class SecKillServiceImpl implements SecKillService {

    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;

    public SecKillServiceImpl(GoodsRepository goodsRepository, OrderRepository orderRepository) {
        this.goodsRepository = goodsRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public int secKill(int id) {

        // 检查商品
        var query = goodsRepository.findById(id);
        if (query.isEmpty()) {
            throw new RuntimeException("商品不存在");
        }

        // 检查库存
        var goods = query.get();
        if (goods.getStock() <= 0) {
            throw new RuntimeException("商品库存不足");
        }

        // 减少库存
        goods.setStock(goods.getStock() - 1);
        goodsRepository.save(goods);

        // 创建订单
        var order = new ShopOrder();
        order.setGoods(goods);
        order.setCount(1);
        order.setTotal(goods.getPrice());
        orderRepository.save(order);

        // 返回订单编号
        return order.getId();
    }
}
