package com.devplayg.vms.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        log.info("##### Request: {} -- {}?{}", req.getMethod(), req.getRequestURI(), req.getQueryString());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("##### Auth-1: name={}, isLogged={}, role={}", auth.getName(), auth.isAuthenticated(), auth.getAuthorities());
        log.info("##### Auth-2: username={}, detail={}", auth.getPrincipal(), auth.getDetails());
        return true;
    }
}