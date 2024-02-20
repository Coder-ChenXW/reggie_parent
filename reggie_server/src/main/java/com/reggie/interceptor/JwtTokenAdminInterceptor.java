package com.reggie.interceptor;

import com.reggie.annotation.IgnoreToken;
import com.reggie.constant.JwtClaimsConstant;
import com.reggie.context.BaseContext;
import com.reggie.properties.JwtProperties;
import com.reggie.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ChenXW
 * @Date:2024/2/19 18:29
 * @Description:
 **/

@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {


    @Resource
    private JwtProperties jwtProperties;

    // 校验jwt
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("jwt校验...");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 获取当前被拦截的方法是否存在IgnoreToken注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        boolean hasMethodAnnotation = handlerMethod.hasMethodAnnotation(IgnoreToken.class);
        if (hasMethodAnnotation) {
            return true;
        }


        String token = request.getHeader(jwtProperties.getAdminTokenName());

        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            BaseContext.setCurrentId(empId);
            // BaseContext.setCurrentId();


            // 通过
            return true;
        } catch (Exception ex) {
            response.setStatus(401);
            return false;
        }
    }
}
