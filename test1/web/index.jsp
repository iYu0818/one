<%--
  Created by IntelliJ IDEA.
  User: chengyu
  Date: 2020/11/5
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>查询界面</title>
  </head>
  <body style="background: url(img/38.jpg)">
  <h2 style="float: right;color: #5bb75b;margin-bottom: 350px">${adminUser.name}欢迎您</h2>
  <div align="center" style="margin-top: 400px">

      <a href="${pageContext.request.contextPath}/findUserByPageServlet" style="color: #0e90d2;cursor: pointer" ><h1>查询所有用户信息</h1></a>

  </div>
  </body>
</html>
