package com.iYu.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/17/19:52
 * @Description:过滤敏感词汇的过滤器(使用动态代理模式)
 */

@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断方法是否是getParameter系列方法
                if (method.getName().equals("getParameter")){
                    String value = (String) method.invoke(req, args);
                    //增强返回值
                    if(value!=null){
                        for (String str : list) {
                            if (value.contains(str)){
                                value = value.replaceAll(str,"***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req,args);
            }
        });

        chain.doFilter(proxy_req, resp);
    }

    /** 
    * @Description: 在init方法中加载配置文件，敏感词汇.txt  
    * @Param: [config]
    * @return: void 
    * @Author: 程煜
    * @Date: 2020/11/17 
    */
    //定义一个集合来存放读取到的敏感词汇
    private List<String> list=new ArrayList<String>();
    public void init(FilterConfig config){
        try {
            //1.获取文件的真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2.读取文件
            BufferedReader br=new BufferedReader(new FileReader(realPath));
            //3.添加数据
            String line=null;
            while((line=br.readLine())!=null){
                list.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
