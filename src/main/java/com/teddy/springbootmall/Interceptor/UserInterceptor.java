package com.teddy.springbootmall.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("userId") == null){
            System.out.println("Session中的 userId 為 null");
            response.setStatus(302); //臨時重新導向，新的臨時性的url放在 response header 的"Location"中返回
//            response.sendRedirect("/user/login");
            return false;
        }
//        System.out.println("Session中的 userId 為 "+request.getSession().getAttribute("userId"));
        return true;
    }
}
