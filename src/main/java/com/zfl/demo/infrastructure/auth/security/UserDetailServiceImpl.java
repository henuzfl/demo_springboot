package com.zfl.demo.infrastructure.auth.security;

import com.zfl.demo.infrastructure.auth.entity.SysUser;
import com.zfl.demo.infrastructure.auth.respository.SysUserRepository;
import com.zfl.demo.infrastructure.auth.vo.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final SysUserRepository sysUserRepository;

    public UserDetailServiceImpl(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("用户名不存在"));
        return new UserPrincipal(sysUser);
    }
}
