package com.zfl.demo.infrastructure.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zfl.demo.infrastructure.auth.entity.SysPermission;
import com.zfl.demo.infrastructure.auth.entity.SysRole;
import com.zfl.demo.infrastructure.auth.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {

    private Long id;
    private String name;
    @JsonIgnore
    private String password;

    private List<String> roles;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(SysUser sysUser) {
        this.setId(sysUser.getId());
        this.setName(sysUser.getName());
        this.setPassword(sysUser.getPassword());
        this.setRoles(sysUser.getRoles().stream().map(SysRole::getName).collect(Collectors.toList()));
        this.setAuthorities(sysUser.getRoles().stream()
                .flatMap(sysRole -> sysRole.getPermissions().stream())
                .map(SysPermission::getName)
                .distinct()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
