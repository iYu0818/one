<%--
  Created by IntelliJ IDEA.
  User: chengyu
  Date: 2020/11/7
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息管理系统</title>
    <!-- 导入css全局样式-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
    </style>
    <script>
        function deleteUser(id) {
            if (confirm("您确定要删除吗?")) {
                location.href = "${pageContext.request.contextPath}/delUserServlet?id=" + id;
            }

        }

        //批量删除用户
        window.onload = function () {
            document.getElementById("delSlected").onclick = function () {
                if (confirm("确认要删除选中的所有吗?")) {
                    //为防止空指针异常，需要先判断一下是否有选中条目
                    var flag = false;//弄一个标志位
                    var ids = document.getElementsByName("uid");
                    for (var i = 0; i < ids.length; i++) {
                        if (ids[i].checked) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        //有一个或多个被选中，才会提交表单
                        document.getElementById("userForm").submit();
                    }
                }
            }
            //全选或全不选
            //获取第一个全选或全不选的checkbox
            document.getElementById("firstcheckbox").onclick = function () {
                //获取下边单个的复选框cbs
                var cbs = document.getElementsByName("uid");
                //遍历cbs，依次将firstcheckbox的checked属性赋给它
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            }
        }

    </script>
</head>
<body style="background: url(img/38.jpg)">
<div class="container">
    <h2 style="text-align: center">用户信息列表</h2>
    <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="name"><strong>姓名:</strong></label>
                <input style="width: 200px;height: 30px;text-align: center" type="text" name="name" class="form-control" value="${condition.name[0]}" id="name"
                       placeholder="请输入要查询的姓名">
            </div>
            <div class="form-group" style="margin-left: 15px">
                <label for="address"><strong>籍贯:</strong></label>
                <input style="width: 200px;height: 30px" type="text" name="address" value="${condition.address[0]}"  class="form-control" id="address"
                       placeholder="请输入要查询的籍贯">
            </div>
            <button style="margin-left: 10px" type="submit" class="btn btn-info" id="select">查询</button>
        </form>
    </div>

    <div style="float: right; margin: 10px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加用户</a>
        <a class="btn btn-danger" href="javascript:void(0)" id="delSlected">删除选中</a>
    </div>
    <form id="userForm" action="${pageContext.request.contextPath}/delSlectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success" style="background:#7ffdd8;text-align: center">
                <th><input type="checkbox" id="firstcheckbox"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>地址</th>
                <th>QQ</th>
                <th>Email</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr style="text-align: center">
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td>
                        <a class="btn btn-info" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-danger" href="javascript:deleteUser(${user.id})">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <%-- 分页功能--%>
    <div style="font-size: 20px">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                    <c:if test="${pb.currentPage==1}">
                        <li class="disabled">
                    </c:if>
                    <c:if test="${pb.currentPage!=1}">
                    <li>
                    </c:if>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage-1}&rows=5&name${condition.name[0]}&address=${condition.address[0]}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage==i}">
                        <li style="margin-left: 10px;margin-right: 10px" class="active">
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name${condition.name[0]}&address=${condition.address[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage!=i}">
                        <li style="margin-left: 10px;margin-right: 10px">
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name${condition.name[0]}&address=${condition.address[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li style="margin-right: 10px">
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage+1}&rows=5&name${condition.name[0]}&address=${condition.address[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 20px;margin-left: 10px;color:#00caff">
                    <strong> 共${pb.totalCount}条数据&nbsp共${pb.totalPage}页</strong>
                </span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
