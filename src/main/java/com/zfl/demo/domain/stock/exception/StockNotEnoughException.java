package com.zfl.demo.domain.stock.exception;

/**
 * @author zhangfeilong
 * @created_at 2022/5/19 10:08
 */
public class StockNotEnoughException extends RuntimeException {

    public StockNotEnoughException() {
        super("库存不足");
    }
}
