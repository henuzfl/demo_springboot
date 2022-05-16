package com.zfl.demo.inboud.controller;

import com.zfl.demo.domain.order.Order;
import com.zfl.demo.domain.order.OrderService;
import io.swagger.annotations.Api;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "订单接口")
@RestController
@RequestMapping("/orders")
@CacheConfig(cacheNames = "controller.order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    @Cacheable(key = "'ALL_ORDERS'")
    public List<Order> list() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public Order get(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "'ALL_ORDERS'"),})
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
