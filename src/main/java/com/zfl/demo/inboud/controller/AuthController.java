package com.zfl.demo.inboud.controller;

import com.zfl.demo.inboud.controller.payload.LoginRequest;
import com.zfl.demo.inboud.controller.vo.JwtResponse;
import com.zfl.demo.infrastructure.auth.security.jwt.JWTFilter;
import com.zfl.demo.infrastructure.auth.security.jwt.TokenProvider;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@Api(tags = "认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    public AuthController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean rememberMe = loginRequest.getRememberMe() != null && loginRequest.getRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtResponse(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }
}
