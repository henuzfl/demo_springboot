package com.zfl.demo.infrastructure.auth.respository;

import com.zfl.demo.infrastructure.auth.entity.SysUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SysUserRepository extends PagingAndSortingRepository<SysUser, Long> {

    Optional<SysUser> findByUsername(String username);
}
