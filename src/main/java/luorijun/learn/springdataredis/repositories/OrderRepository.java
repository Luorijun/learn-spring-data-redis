package luorijun.learn.springdataredis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luorijun.learn.springdataredis.entities.ShopOrder;

@Repository
public interface OrderRepository extends JpaRepository<ShopOrder, Integer> {
}
