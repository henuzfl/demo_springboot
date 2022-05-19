package com.zfl.demo.domain.stock;

import com.zfl.demo.domain.stock.exception.StockNotEnoughException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock getById(long id) {
        return stockRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("stock not found"));
    }

    public Page<Stock> getList(String name, int pageNum, int pageSize) {
        Specification<Stock> specification =
                (root, criteriaQuery, criteriaBuilder) -> {
                    List<Predicate> predicates = new LinkedList<>();
                    if (StringUtils.isNotBlank(name)) {
                        predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                };
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
        return stockRepository.findAll(specification, pageable);
    }


    public void deduct(long id, int count) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("stock not found"));
        if (stock.getCount() < count) {
            throw new StockNotEnoughException();
        }
        stock.setCount(stock.getCount() - count);
        stockRepository.save(stock);
    }

    public void delete(long id) {
        stockRepository.deleteById(id);
    }

    public Stock create(String name, int count, int price) {
        Stock stock = new Stock();
        stock.setName(name);
        stock.setCount(count);
        stock.setPrice(price);
        return stockRepository.save(stock);
    }
}
