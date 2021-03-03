package luorijun.learn.springdataredis.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import luorijun.learn.springdataredis.entities.ShopOrder;
import luorijun.learn.springdataredis.repositories.OrderRepository;
import luorijun.learn.springdataredis.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ShopOrder> getList() {
        return repository.findAll();
    }

    @Override
    public ShopOrder getById(int id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("订单不存在");
        });
    }
}
