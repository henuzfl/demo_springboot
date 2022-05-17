package com.zfl.demo.domain.stock;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StockRepository extends PagingAndSortingRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {
}
