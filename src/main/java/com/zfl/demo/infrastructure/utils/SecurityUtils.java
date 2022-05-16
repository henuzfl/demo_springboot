package com.zfl.demo.infrastructure.utils;

import com.mchange.rmi.NotAuthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static UserDetails getCurrentSysUserDetails() throws NotAuthorizedException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NotAuthorizedException();
        }
        return (UserDetails) authentication.getPrincipal();
    }
}
