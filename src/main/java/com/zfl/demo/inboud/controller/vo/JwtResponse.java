package com.zfl.demo.inboud.controller.vo;

import lombok.Data;

@Data
public class JwtResponse {

    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }
}
