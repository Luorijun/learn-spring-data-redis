package luorijun.learn.springdataredis.services;

import java.util.List;

import luorijun.learn.springdataredis.entities.ShopGoods;

public interface GoodsService {

    List<ShopGoods> getList();

    ShopGoods getById(int id);
}
