package com.zfl.demo.application;

import com.mchange.rmi.NotAuthorizedException;
import com.zfl.demo.domain.account.Account;
import com.zfl.demo.domain.account.AccountService;
import com.zfl.demo.domain.order.OrderService;
import com.zfl.demo.domain.stock.Stock;
import com.zfl.demo.domain.stock.StockService;
import com.zfl.demo.infrastructure.auth.entity.SysUser;
import com.zfl.demo.infrastructure.auth.service.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusinessService {

    private final AccountService accountService;

    private final StockService stockService;

    private final OrderService orderService;

    private final SecurityService securityService;

    public BusinessService(AccountService accountService, StockService stockService, OrderService orderService, SecurityService securityService) {
        this.accountService = accountService;
        this.stockService = stockService;
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void purchase(long stockId, int amount) throws NotAuthorizedException {
        SysUser sysUser = securityService.loadCurrentSysUser();
        Account account = accountService.getBySysUserId(sysUser.getId());
        Stock stock = stockService.getById(stockId);
        stockService.deduct(stockId, amount);
        int money = stock.getPrice() * amount;
        accountService.debit(account.getId(), money);
        orderService.create(account, stock, amount, money);
    }
}
