package com.zfl.demo.inboud.controller.request;

import lombok.Data;

@Data
public class PurchaseRequest {
    private long stockId;
    private int amount;
}
