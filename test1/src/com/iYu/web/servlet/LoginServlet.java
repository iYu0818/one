package com.iYu.web.servlet;

import com.iYu.entity.Admin;
import com.iYu.service.UserService;
import com.iYu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/05/18:06
 * @Description:
 */

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        //2.1获取用户填写的验证码
        String verifycode = request.getParameter("verifycode");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.2校验验证码
        HttpSession session = request.getSession();
        String checkcode = (String)(session.getAttribute("CHECKCODE"));
        session.removeAttribute("CHECKCODE");//移除session，确保验证码的一次性
        if (verifycode==null||verifycode==""){
            request.setAttribute("login_error","请输入验证码");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else if (!checkcode.equalsIgnoreCase(verifycode)){
            request.setAttribute("login_error","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }/*else if (username==null||password==null){
            request.setAttribute("login_error","用户名或密码为空");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } */else{
            //3.封装Admin对象
            Map<String, String[]> map = request.getParameterMap();
            Admin admin=new Admin();
            try {
                BeanUtils.populate(admin,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //4.调用service层的方法
            UserService service=new UserServiceImpl();
            Admin adminUser = service.login(admin);
            if (adminUser!=null){
                session.setAttribute("adminUser",adminUser);
                //客户端跳转
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }else{
                request.setAttribute("login_error","用户名或密码错误");
                //服务器端跳转
                request.getRequestDispatcher("/login.jsp").forward(request,response);
             }
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
