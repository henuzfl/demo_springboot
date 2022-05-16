package com.zfl.demo.inboud.controller;

import com.zfl.demo.domain.stock.Stock;
import com.zfl.demo.domain.stock.StockService;
import io.swagger.annotations.Api;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "库存接口")
@RestController
@RequestMapping("/stocks")
@CacheConfig(cacheNames = "controller.stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    @Cacheable(key = "'ALL_STOCKS'")
    public List<Stock> list() {
        return stockService.getAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public Stock get(@PathVariable Long id) {
        return stockService.getById(id);
    }

    @DeleteMapping("/{id}")
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "'ALL_STOCKS'"),})
    public void delete(@PathVariable Long id) {
        stockService.delete(id);
    }
}
