package com.zfl.demo.domain.order;

import com.google.common.collect.Lists;
import com.zfl.demo.domain.account.Account;
import com.zfl.demo.domain.stock.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void create(Account account, Stock stock, Integer count, Integer money) {
        Order order = new Order();
        order.setAccount(account);
        order.setStock(stock);
        order.setCount(count);
        order.setMoney(money);
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return Lists.newArrayList(orderRepository.findAll());
    }

    public Order getById(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("order not found"));
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
