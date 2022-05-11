package com.zfl.demo.infrastructure.auth.respository;

import com.zfl.demo.infrastructure.auth.entity.SysRole;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysRoleRepository extends PagingAndSortingRepository<SysRole, Long> {
}
