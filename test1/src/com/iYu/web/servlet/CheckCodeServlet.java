package com.iYu.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 程煜
 * @Date: 2020/11/02/15:54
 * @Description:
 */

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");
        int width=80;
        int height=40;
        //创建一个图片对象
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        //美化图片
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.pink);//填充颜色
        g.fillRect(0,0,width,height);
        //使用方法随机产生4个码
        String checkCode = getCheckCode();
        //将验证码存入Session域中
        request.getSession().setAttribute("CHECKCODE",checkCode);
        //将画笔设为黄色
        g.setColor(Color.YELLOW);
        //设置字体的大小
        g.setFont(new Font("黑体",Font.BOLD,30));
        g.drawRect(0,0,width-1,height-1);
        g.drawString(checkCode,7,30);
        //显示图片
        ImageIO.write(image,"png",response.getOutputStream());

    }
    private String getCheckCode(){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";//定义随机出现的常量
        int size=str.length();
        //生成随机角标
        Random ran=new Random();
        StringBuilder sb=new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(size);
            //在str中获取下标为index的字符
            char ch = str.charAt(index);
            sb.append(ch);
        }
        return sb.toString();
    }
}

