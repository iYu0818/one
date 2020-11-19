package com.iYu.web.servlet;

import com.iYu.entity.PageBean;
import com.iYu.entity.User;
import com.iYu.service.UserService;
import com.iYu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/09/22:02
 * @Description:
 */

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //接受条件查询的参数
        Map<String, String[]> condition = request.getParameterMap();
        request.setAttribute("condition",condition);
        if (currentPage==null||currentPage==""){
            currentPage="1";
        }
        if (rows==null||rows==""){
            rows="5";
        }
        //调用Service查询PageBean对象
        UserService service=new UserServiceImpl();
        //调用findUserByPage方法返回PageBean对象
        PageBean<User> pb= service.findUserByPage(currentPage,rows,condition);
        //将对象存入request域中并转发至list.jsp
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
