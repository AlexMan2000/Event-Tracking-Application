package com.example.emsbackend.jwt;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.emsbackend.dto.UserEntityDTO;
import com.example.emsbackend.exception.LoginException;
import com.example.emsbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;


    // 请求被相应的Controller处理之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头中获取token字段的值
        String tokenValue = request.getHeader("token");
        // isBlank 用于判断一个string中是否有各种形式的空格或者是否为空
        if(StrUtil.isBlank(tokenValue)) {
            // 表示请求头中没有带token, 于是在url的参数列表中寻找 url?token=tokenValue
            tokenValue = request.getParameter("token");
        }


        // 如果url中也没有，抛出401异常，无权限
        if (StrUtil.isBlank(tokenValue)) {
            throw new LoginException("401", "Not Authroized");
        }

        String userId;

        try {
            userId = JWT.decode(tokenValue).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new LoginException("500", "Internal Server Error");
        }

        UserEntityDTO userEntityDTO = userService.getUserDTOById(Long.valueOf(userId));

        if (userEntityDTO == null) {
            throw new LoginException("401", "Not Authroized");
        }


        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userEntityDTO.getPasswordHash())).build();

        try {
            jwtVerifier.verify(tokenValue);
        } catch (JWTVerificationException e) {
            throw new LoginException("401", "Access denied");
        }

        return true;
    }
}
