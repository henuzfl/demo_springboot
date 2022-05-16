package com.zfl.demo.infrastructure.auth.service;

import com.mchange.rmi.NotAuthorizedException;
import com.zfl.demo.infrastructure.auth.entity.SysUser;
import com.zfl.demo.infrastructure.auth.respository.SysUserRepository;
import com.zfl.demo.infrastructure.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final SysUserRepository sysUserRepository;

    public SecurityService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public SysUser loadCurrentSysUser() throws NotAuthorizedException {
        UserDetails userDetails = SecurityUtils.getCurrentSysUserDetails();
        return sysUserRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new NotAuthorizedException("用户不存在"));
    }
}
