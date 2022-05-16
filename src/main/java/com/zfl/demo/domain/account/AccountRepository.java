package com.zfl.demo.domain.account;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    Optional<Account> findBySysUserId(Long sysUserId);
}
