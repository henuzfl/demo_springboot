package com.zfl.demo.inboud.controller.request;

import lombok.Data;

@Data
public class StockAddRequest {

    private String name;
    private Integer count;
    private Integer price;
}
