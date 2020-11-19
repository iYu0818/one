package com.iYu.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/14/16:41
 * @Description:完成所有登录验证
 */

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.强制转换
        HttpServletRequest request= (HttpServletRequest) req;
        //2.获取资源请求路径
        String uri = request.getRequestURI();
        //3.判断是否包含登陆相关的资源，要注意排除css，js，字体，图片，验证码等资源
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/checkCodeServlet") || uri.contains("/img/")){
               //是登录相关资源，直接放行
            chain.doFilter(req,resp);
        }else {
            //不是登录相关资源，需校验是否登录
            Object adminUser = request.getSession().getAttribute("adminUser");
            if (adminUser!=null){
                chain.doFilter(req,resp);
            }else {
                request.setAttribute("login_error","您尚未登录,请先登录!");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
