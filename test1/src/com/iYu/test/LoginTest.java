package com.iYu.test;

import com.iYu.dao.UserDao;
import com.iYu.dao.impl.UserDaoImpl;
import com.iYu.entity.Admin;
import com.iYu.entity.User;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/06/13:53
 * @Description:
 */

public class LoginTest {
    @Test
    public void test1(){
        UserDao dao=new UserDaoImpl();
        Admin admin = dao.findUserByUsernameAndPassword("chengyu", "9527");
        System.out.println(admin);
    }
}
