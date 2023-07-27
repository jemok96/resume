package com.resume.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try{
            if(ex instanceof PostNotFountException){
                log.info("PostNotFountException resolver to 400");
                response.sendRedirect("/error/404.html");
                return new ModelAndView();
            }
        }catch (IOException e){
            log.error("resolver ex",e);
        }
        return null;
    }
}
