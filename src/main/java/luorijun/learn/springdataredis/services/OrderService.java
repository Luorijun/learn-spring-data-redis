package luorijun.learn.springdataredis.services;

import java.util.List;

import luorijun.learn.springdataredis.entities.ShopOrder;

public interface OrderService {

    List<ShopOrder> getList();

    ShopOrder getById(int id);
}
