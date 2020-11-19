/*
package com.iYu.web.servlet;

import com.iYu.entity.User;
import com.iYu.service.UserService;
import com.iYu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

*/
/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/07/15:08
 * @Description:
 *//*


@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     UserService service=new UserServiceImpl();
        List<User> users = service.findAll();
        request.getSession().setAttribute("users",users);
        response.sendRedirect(request.getContextPath()+"/list.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
*/
