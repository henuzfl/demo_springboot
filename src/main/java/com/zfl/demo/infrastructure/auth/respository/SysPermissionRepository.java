package com.zfl.demo.infrastructure.auth.respository;

import com.zfl.demo.infrastructure.auth.entity.SysPermission;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysPermissionRepository extends PagingAndSortingRepository<SysPermission, Long> {
}
