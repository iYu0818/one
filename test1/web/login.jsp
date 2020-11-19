<%--
  Created by IntelliJ IDEA.
  User: chengyu
  Date: 2020/11/5
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viweport" content="width=device-width,initial-scale=1"/>
    <title>登陆主界面</title>
    <!-- 导入css全局样式-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function refreshCode(){
            //切换验证码
            var vcode  =document.getElementById("vcode");
            vcode.src="${pageContext.request.contextPath}/checkCodeServlet?time"+new Date().getTime();
        }
    </script>
</head>
<body style="background: url(img/38.jpg)">
<div class="container" align="center">
    <h3 style="text-align: center">管理员登录</h3>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post" style="text-align: center" class="">
        <table align="center">
            <div class="form-group">
                <tr>
                    <td style="font-size: 16px"><strong>用户名:</strong></td>
                    <td><input style="width: 300px;height: 35px" type="text" name="username" class="from-control" id="username" placeholder="&nbsp;&nbsp;请输入用户名" value="${username}"/>
                    </td>

                </tr>
                <tr>
                    <td style="font-size: 16px"><strong>密码:</strong></td>
                    <td>
                        <input style="width: 300px;height: 35px" type="password" name="password"  id="password" placeholder="&nbsp;&nbsp;请输入密码" value="${password}"/>
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 16px"><strong>验证码:</strong></td>
                    <td>
                        <input style="width: 300px;height: 35px" type="text" name="verifycode" class="from-control" id="verifycode" placeholder="&nbsp;&nbsp;请输入验证码" style="..."/>
                    </td>
                </tr>
                <tr align="center">
                    <td colspan="2">
                        <a href="javascript:refreshCode();">
                            <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/>
                        </a>
                    </td>
                </tr>
                <tr align="center">
                    <td style="margin-left: 20px">
                        <input class="btn btn btn-primary" type="submit" value="登录"/>
                    </td>
                    <td style="float: right">
                        <input class="btn btn btn-danger" type="reset" value="重置"/>
                    </td>
                </tr>
            </div>
        </table>
        <hr/>
        <!--出错显示的信息框-->
        <div class="alert alert-warning alert-dismissible">
            <button type="button" class="close" data-dismiss="alert">
                <span></span>
            </button>
            <strong style="color: red">${login_error}</strong>
        </div>
    </form>
</div>
</body>
</html>
