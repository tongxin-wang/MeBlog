package com.lzw.meblog.interceptor;

import com.lzw.meblog.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对/admin开头后台管理请求（除/admin/login）的拦截器
 * @author TongxinWang
 */
public class BackInterceptor implements HandlerInterceptor {
    /* 后台登录账号密码 */
    private static String username = "admin";
    private static String password = "justfortest";

    /**
     * 方法在请求处理之前被调用，用来进行一些前置初始化操作或是对当前请求做预处理
     * @param request
     * @param response
     * @param handler
     * @return 布尔值（判断请求是否继续执行）
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        boolean isValid;
        //取session中的信息
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            isValid = false;
        }
        else{
            //对用户名和密码进行验证
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                isValid = true;
            }
            else{
                isValid = false;
            }
        }

        return isValid;
    }
}
