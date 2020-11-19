package com.iYu.service;

import com.iYu.entity.Admin;
import com.iYu.entity.PageBean;
import com.iYu.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/05/22:14
 * @Description:
 */

public interface UserService {
    /**
    * @Description:  查看数据库所有的用户
    * @Param: []
    * @return: java.util.List<com.iYu.entity.User>
    * @Author: 程煜
    * @Date: 2020/11/8
    */
   List<User> findAll();

   /**
   * @Description: 登陆验证，根据账号密码来返回对象
   * @Param: [admin]
   * @return: com.iYu.entity.Admin
   * @Author: 程煜
   * @Date: 2020/11/8
   */
   Admin login(Admin admin);

   /**
   * @Description:用户添加
   * @Param: [addUser]
   * @return: void
   * @Author: 程煜
   * @Date: 2020/11/8
   */
   void addUser(User addUser);

   /**
   * @Description: 根据id删除用户信息
   * @Param: [parseInt]
   * @return: void
   * @Author: 程煜
   * @Date: 2020/11/8
   */
    void deleteUser(int id);

    /**
    * @Description: 根据id查到单个用户信息
    * @Param: [parseInt]
    * @return: void
    * @Author: 程煜
    * @Date: 2020/11/8
    */
    User findUserById(int parseInt);

    /** 
    * @Description: 根据id修改用户信息 
    * @Param: [user] 
    * @return: void 
    * @Author: 程煜
    * @Date: 2020/11/8 
    */
    void updateUserById(User user);

    /**
    * @Description: 批量删除用户
    * @Param: [ids]
    * @return: void
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    void delSlectedUser(String[] ids);
    
    /** 
    * @Description: 分页条件查询
    * @Param: [currentPage, rows] 
    * @return: com.iYu.entity.PageBean<com.iYu.entity.User> 
    * @Author: 程煜
    * @Date: 2020/11/9 
    */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
