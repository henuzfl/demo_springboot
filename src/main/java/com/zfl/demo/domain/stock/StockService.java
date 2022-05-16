package com.zfl.demo.domain.stock;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock getById(long id) {
        return stockRepository.findById(id).orElseThrow(() -> new RuntimeException("stock not found"));
    }

    public List<Stock> getAll() {
        return Lists.newArrayList(stockRepository.findAll());
    }

    public void deduct(long id, int count) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new RuntimeException("stock not found"));
        if (stock.getCount() < count) {
            throw new RuntimeException("stock not enough");
        }
        stock.setCount(stock.getCount() - count);
        stockRepository.save(stock);
    }

    public void delete(long id) {
        stockRepository.deleteById(id);
    }
}
