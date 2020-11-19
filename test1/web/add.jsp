<%--
  Created by IntelliJ IDEA.
  User: chengyu
  Date: 2020/11/7
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <!--使用Edge最新的浏览器渲染方式-->
<%--    <meta http-equiv="X-UA-Compatible" content="IE-edge">--%>
    <!--viewport视口:网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width:默认宽度与设备的宽度相同
    initial-scale:初始缩放比为1:1
    -->
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <!-- 导入css全局样式-->
    <link  rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>添加用户</title>
</head>
<body style="background: url(img/38.jpg)">
<div class="container" >
    <center><h2>添加用户页面</h2></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" style="text-align: center" method="post">
        <div class="form-group">
            <label for="name"><strong>姓名:</strong></label>
            <input style="width: 500px;height: 35px;margin: auto" type="text" class="form-control" id="name" name="name" placeholder="请输入姓名"/>
        </div>

        <div class="form-group" style="margin-bottom: 15px">
            <label><strong>性别:</strong></label>
            <input type="radio" name="gender" value="男"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>
        <div class="form-group">
            <label for="age"><strong>年龄:</strong></label>
            <input style="width: 500px;height: 35px;margin: auto" type="text" class="form-control" id="age" name="age" placeholder="请输入年龄"/>
        </div>
        <div class="form-group">
            <label for="address"><strong>籍贯</strong>:</label>
            <select name="address" class="form-control" id="address" style="width: 500px;height: 35px;margin: auto">
                <option value="四川">四川</option>
                <option value="首尔">首尔</option>
                <option value="北京">北京</option>
            </select>
        </div>
        <div class="form-group">
            <label for="QQ"><strong>QQ:</strong></label>
            <input style="width: 500px;height: 35px;margin: auto" type="text" class="form-control" id="qq" name="qq" placeholder="请输入QQ号码"/>
        </div>
        <div class="form-group">
            <label for="email"><strong>Email:</strong></label>
            <input style="width: 500px;height: 35px;margin: auto" type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱"/>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="提交"/>
            <input type="reset" class="btn btn-danger" value="重置"/>
            <input type="button" onclick="javascript:history.back(-1);" class="btn btn-light" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>
