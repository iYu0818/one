package com.iYu.service.impl;

import com.iYu.dao.UserDao;
import com.iYu.dao.impl.UserDaoImpl;
import com.iYu.entity.Admin;
import com.iYu.entity.PageBean;
import com.iYu.entity.User;
import com.iYu.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/05/22:14
 * @Description:
 */

public class UserServiceImpl implements UserService {
    //注入dao
    private UserDao dao=new UserDaoImpl();
    /** 
    * @Description: 查看数据库所有的用户 
    * @Param: [] 
    * @return: java.util.List<com.iYu.entity.User> 
    * @Author: 程煜
    * @Date: 2020/11/9 
    */
    @Override
    public List<User> findAll() {
        return dao.finAll();
    }

    /** 
    * @Description: 登陆验证，根据账号密码来返回对象  
    * @Param: [admin] 
    * @return: com.iYu.entity.Admin 
    * @Author: 程煜
    * @Date: 2020/11/9 
    */
    @Override
    public Admin login(Admin admin) {
        return dao.findUserByUsernameAndPassword(admin.getUsername(),admin.getPassword());
    }

    /** 
    * @Description: 用户添加 
    * @Param: [addUser] 
    * @return: void 
    * @Author: 程煜
    * @Date: 2020/11/9 
    */
    @Override
    public void addUser(User addUser) {
        dao.add(addUser);
    }
    
    /** 
    * @Description: 根据id删除用户信息 
    * @Param: [id] 
    * @return: void 
    * @Author: 程煜
    * @Date: 2020/11/9 
    */
    @Override
    public void deleteUser(int id) {
        dao.deleteUser(id);
    }

    /**
     * @Description: 根据id查到单个用户信息
     * @Param: [id]
     * @return: com.iYu.entity.User
     * @Author: 程煜
     * @Date: 2020/11/9
     */
    @Override
    public User findUserById(int id) {
        return dao.findUserById(id);
    }

    /** 
    * @Description: 根据id修改用户信息
     * @Param: [user] 
    * @return: void 
    * @Author: 程煜
    * @Date: 2020/11/9 
    */
    @Override
    public void updateUserById(User user) {
        dao.updateUserById(user);
    }

    /**
    * @Description: 批量删除用户
    * @Param: [ids]
    * @return: void
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    @Override
    public void delSlectedUser(String[] ids) {
        //遍历数组进行删除
        //判断是否为空
        if (ids!=null&&ids.length>0){
            for (String id : ids) {
                //将String类型的转换为int
                dao.deleteUser(Integer.parseInt(id));
            }
        }

    }

 /**
 * @Description: 分页条件查询
 * @Param: [_currentPage, _rows, condition]
 * @return: com.iYu.entity.PageBean<com.iYu.entity.User>
 * @Author: 程煜
 * @Date: 2020/11/13
 */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        //将两个参数转换类型至int
        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);
        if (currentPage<=1){
            currentPage=1;
        }
        //创建空的PageBean对象
        PageBean<User> pb=new PageBean<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao查询总的记录数,并传递condition这个map集合的条件完成条件查询
        int totalCount=dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //算出查询的起始值索引,返回Pagebean对象带分页效果的,给带条件condition
        int start=(currentPage-1)*rows;
        List<User> list=dao.findByPage(start,rows,condition);
        pb.setList(list);
        //算出总的页码数
        int totalPage=(totalCount%rows==0) ? totalCount/rows : totalCount/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
