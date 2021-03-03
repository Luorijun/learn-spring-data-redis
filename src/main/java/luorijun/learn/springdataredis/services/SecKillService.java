package luorijun.learn.springdataredis.services;

public interface SecKillService {

    int secKillWithPessimisticLock(int id);
}
