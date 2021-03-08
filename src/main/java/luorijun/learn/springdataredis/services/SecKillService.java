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
     * 秒杀：使用 redis 分布式锁
     *
     * @param id 商品 id
     * @return 购买成功后的订单 id
     */
    int secKillWithDistributedLock(int id);
}
