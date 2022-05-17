package com.zfl.demo.inboud.controller;

import com.zfl.demo.domain.stock.Stock;
import com.zfl.demo.domain.stock.StockService;
import com.zfl.demo.inboud.controller.request.StockAddRequest;
import com.zfl.demo.inboud.controller.request.StockPageRequest;
import io.swagger.annotations.Api;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Page<Stock> list(
            StockPageRequest stockPageRequest,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        return stockService.getList(stockPageRequest.getName(), pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public Stock get(@PathVariable Long id) {
        return stockService.getById(id);
    }

    @PostMapping
    @CacheEvict(key = "'ALL_STOCKS'")
    public Stock create(@Valid @RequestBody StockAddRequest stockAddRequest) {
        return stockService.create(stockAddRequest.getName(), stockAddRequest.getCount(), stockAddRequest.getPrice());
    }

    @DeleteMapping("/{id}")
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "'ALL_STOCKS'"),})
    public void delete(@PathVariable Long id) {
        stockService.delete(id);
    }
}
