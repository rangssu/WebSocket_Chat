package com.example.websocket_chat.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class WsFilter implements Filter  {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter 실행");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("==========필터 시작!==========");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("==========필터 종료!==========");
    }

    @Override
    public void destroy() {
        log.info("Filter 종료");
    }

}
