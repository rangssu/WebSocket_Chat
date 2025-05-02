package com.example.websocket_chat.annotation;

import com.example.websocket_chat.entity.Users;
import com.example.websocket_chat.exception.ExceptionCode;
import com.example.websocket_chat.exception.WsChatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginAuth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = httpServletRequest.getSession(false);

        // 단일 책임 원칙
        if (session == null){
            throw new WsChatException(ExceptionCode.UNAUTHENTICATED);
        }

        Users user = (Users) session.getAttribute("loginSession");

        if (user == null)  {
            throw new WsChatException(ExceptionCode.UNAUTHENTICATED);
        }

        // 리턴으로 보낼껀 MemberAuth 임. user 에서 꺼내서 보내주면 됨,
        return null;
    }
}
