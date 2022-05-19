package com.zfl.demo.domain.account.exception;

/**
 * @author zhangfeilong
 * @created_at 2022/5/19 10:05
 */
public class AccountBalanceNotEnoughException extends RuntimeException {

    public AccountBalanceNotEnoughException() {
        super("账户余额不足");
    }

}
