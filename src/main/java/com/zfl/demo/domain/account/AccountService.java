package com.zfl.demo.domain.account;

import com.zfl.demo.domain.account.exception.AccountBalanceNotEnoughException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void debit(Long userId, int num) {
        Account account = accountRepository.findBySysUserId(userId).orElseThrow(() -> new EntityNotFoundException("账户不存在"));
        if (account.getMoney() < num) {
            throw new AccountBalanceNotEnoughException();
        }
        account.setMoney(account.getMoney() - num);
        accountRepository.save(account);
    }

    public Account getBySysUserId(Long userId) {
        return accountRepository.findBySysUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("账户不存在"));
    }
}
