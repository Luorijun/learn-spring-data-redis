package luorijun.learn.springdataredis.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import luorijun.learn.springdataredis.entities.ShopGoods;
import luorijun.learn.springdataredis.repositories.GoodsRepository;
import luorijun.learn.springdataredis.services.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository repository;

    public GoodsServiceImpl(GoodsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ShopGoods> getList() {
        return repository.findAll();
    }

    @Override
    public ShopGoods getById(int id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("商品不存在");
        });
    }
}
