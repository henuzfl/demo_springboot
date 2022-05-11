package com.zfl.demo.inboud.controller;

import com.zfl.demo.inboud.controller.payload.LoginRequest;
import com.zfl.demo.infrastructure.auth.vo.UserPrincipal;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Api(tags = "认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        JwtBuilder builder = Jwts.builder().setId(userPrincipal.getId().toString()).setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "test").claim("roles", userPrincipal.getRoles()).claim("authorities", userPrincipal.getAuthorities());
        return builder.compact();
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {

    }
}
