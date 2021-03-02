package luorijun.learn.springdataredis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luorijun.learn.springdataredis.entities.Goods;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
