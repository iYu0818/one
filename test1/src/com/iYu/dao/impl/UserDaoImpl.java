package com.iYu.dao.impl;

import com.iYu.dao.UserDao;
import com.iYu.entity.Admin;
import com.iYu.entity.User;
import com.iYu.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/05/14:53
 * @Description:
 */

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
    * @Description: 查看数据库所有的用户
    * @Param: []
    * @return: java.util.List<com.iYu.entity.User>
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    @Override
    public List<User> finAll() {
       String sql="select * from user";
        List<User>  list= template.query(sql,new BeanPropertyRowMapper<User>(User.class));
        return list;
    }

    /**
    * @Description: 登陆验证，根据账号密码来返回对象
    * @Param: [username, password]
    * @return: com.iYu.entity.Admin
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    @Override
    public Admin findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql="select * from admin where username=? and password=?";
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class),username,password);
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
    * @Description: 用户添加
    * @Param: [addUser]
    * @return: void
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    @Override
    public void add(User addUser) {
        //定义sql
        String sql="insert into user values(null,?,?,?,?,?,?)";
        template.update(sql,addUser.getName(),addUser.getGender(),addUser.getAge(),addUser.getAddress(),addUser.getQq(),addUser.getEmail());

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
        String sql="delete from user where id=?";
        template.update(sql,id);
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
        String sql="select * from user where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
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
        String sql="update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    /**
    * @Description: 查询总的记录数
    * @Param: []
    * @return: int
    * @Author: 程煜
    * @Date: 2020/11/9
     * @param condition
    */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义初始化sql
        String sql="select count(*) from user where 1=1 ";
        //2.完成字符串连接对象的创建
        StringBuilder sb=new StringBuilder(sql);
        //3.创建一个arraylist的集合来存?的值
        List<Object> parameters=new ArrayList<Object>();
        //4.遍历sql
        Set<String> set = condition.keySet();
        for (String key : set) {
            //排除提交currentPage和rows的情况
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if (value!=null&& !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                parameters.add("%"+value+"%");
            }

        }
        return template.queryForObject(sb.toString(),Integer.class,parameters.toArray());
    }
    /**
    * @Description: 根据索引和查询长度来查询数据库
    * @Param: [start, rows]
    * @return: java.util.List<com.iYu.entity.User>
    * @Author: 程煜
    * @Date: 2020/11/9
    */
    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        //初始化sql
        String sql="select * from user where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        //3.创建一个arraylist的集合来存?的值
        List<Object> parameters=new ArrayList<Object>();
        //4.遍历sql
        Set<String> set = condition.keySet();
        for (String key : set) {
            //排除提交currentPage和rows的情况
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if (value!=null&& !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                parameters.add("%"+value+"%");
            }

        }
        sb.append("limit ?,? ");
        parameters.add(start);
        parameters.add(rows);
        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),parameters.toArray());
    }

}
