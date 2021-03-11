package luorijun.learn.springdataredis.services;

public interface SecKillService {

    /**
     * 秒杀：使用 mysql 悲观锁
     *
     * @param id 商品 id
     * @return 购买成功后的订单 id
     */
    int secKillWithPessimisticLock(int id);

    /**
     * 秒杀：使用 redis 缓存减库存，TODO: rabbitmq 异步处理订单
     *
     * @param id 商品 id
     */
    void secKillWithCacheAndAsyncOrder(int id);
}
