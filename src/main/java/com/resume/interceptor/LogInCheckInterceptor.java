package com.resume.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LogInCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = String.valueOf(request.getRequestURL());
        log.info("인증 체크 인터셉터 url={}",url);
        HttpSession session =request.getSession(false);

        log.info("session={}",session);
        if(session ==null ||session.getAttribute("userSession") ==null){
            log.info("미인증 사용자");
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }


}
