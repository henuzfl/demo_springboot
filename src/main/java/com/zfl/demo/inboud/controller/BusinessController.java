package com.zfl.demo.inboud.controller;

import com.mchange.rmi.NotAuthorizedException;
import com.zfl.demo.application.BusinessService;
import com.zfl.demo.inboud.controller.request.PurchaseRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "示例业务接口")
@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    public void create(@Valid PurchaseRequest purchaseRequest) throws NotAuthorizedException {
        businessService.purchase(purchaseRequest.getStockId(), purchaseRequest.getAmount());
    }
}
