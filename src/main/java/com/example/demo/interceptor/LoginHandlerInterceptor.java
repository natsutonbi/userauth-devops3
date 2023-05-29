package com.example.demo.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LoginHandlerInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception
    {
        Object user = request.getSession().getAttribute("loginUser");
        if(user==null){
            Cookie[] cookies=request.getCookies();
            if(cookies==null){//客户端未携带Cookie
                //未经过验证
                request.setAttribute("msg", "没权限请先登录");
                response.sendRedirect("user/login");
                return false;
            }
            String isLogin=null;
            for(Cookie item:cookies)
            {
                if("isLogin".equals(item.getName())){
                    isLogin=item.getValue();
                    break;
                }
            }
            if(StringUtils.isEmpty(isLogin) ||
                ! ("true".equals(isLogin))
                )
            {
                response.sendRedirect("user/login");
                return false;
            }
            //通过cookie验证

        }
        //服务端session显示已经登录
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object
    handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object
    handler,@Nullable Exception ex) throws Exception {
    }

}
