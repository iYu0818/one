package com.iYu.dao;

import com.iYu.entity.Admin;
import com.iYu.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/05/22:46
 * @Description:
 */

public interface UserDao {
    /**
    * @Description: 查询所有用户
    * @Param: []
    * @return: java.util.List<com.iYu.entity.User>
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    List<User> finAll();

    /**
    * @Description: 登陆验证，根据账号密码来返回对象
    * @Param: [username, password]
    * @return: com.iYu.entity.Admin
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    Admin findUserByUsernameAndPassword(String username, String password);

    /**
    * @Description: 用户添加
    * @Param: [addUser]
    * @return: void
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    void add(User addUser);

    /**
    * @Description: 根据id删除用户信息
    * @Param: [id]
    * @return: void
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    void deleteUser(int id);

    /**
    * @Description: 根据id查到单个用户信息
    * @Param: [id]
    * @return: com.iYu.entity.User
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    User findUserById(int id);

    /**
    * @Description: 根据id修改用户信息
     * @Param: [user]
    * @return: void
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    void updateUserById(User user);

    /**
    * @Description: 待条件的查询总记录数
    * @Param: [condition]
    * @return: int
    * @Author: 程煜
    * @Date: 2020/11/13
    */
    int findTotalCount(Map<String, String[]> condition);

    /**
    * @Description: 分页条件查询
    * @Param: [start, rows, condition]
    * @return: java.util.List<com.iYu.entity.User>
    * @Author: 程煜
    * @Date: 2020/11/13
    */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

}
