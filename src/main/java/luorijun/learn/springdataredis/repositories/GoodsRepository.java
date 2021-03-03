package luorijun.learn.springdataredis.repositories;

import java.util.Optional;
import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import luorijun.learn.springdataredis.entities.ShopGoods;

@Repository
public interface GoodsRepository extends JpaRepository<ShopGoods, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select g from ShopGoods g where g.id=?1")
    Optional<ShopGoods> findByIdWithWriteLock(int id);
}
