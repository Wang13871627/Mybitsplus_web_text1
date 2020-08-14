<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/13
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工信息</title>
</head>
<script src="jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    var add=function () {
        window.location.href="/employee/empa.do";
    }
</script>
<body>
<center>
<h2><font color="blue">员工信息</font></h2>
<input type="button" style="background-color: aqua" onclick="add()" value="添加"/><br>
    <form action="/employee/emps.do" method="post">
        姓名：<input type="text" name="name"/>
        职位：
        <c:forEach items="${list2}" var="c">
            ${c.pname}<input type="checkbox" name="postid" value="${c.pid}"/>
        </c:forEach>
        部门：<select name="deptid">
        <option value="0">请选择</option>
        <c:forEach items="${list1}" var="a">
            <option value="${a.did}">${a.dname}</option>
        </c:forEach>
    </select>
        <input type="submit" value="搜索"/>
    </form>
<c:choose>
    <c:when test="${list.size()==0}">
        <font color="#bdb76b">当前无员工信息</font>
    </c:when>
    <c:otherwise>
        <table width="70%" border="1">
            <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>爱好</th>
                <th>身份证正</th>
                <th>身份证背</th>
                <th>职位</th>
                <th>部门</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${list}" var="b">
                <tr align="center">
                    <td>${b.name}</td>
                    <td>${b.sex}</td>
                    <td>${b.age}</td>
                    <td>${b.hobby}</td>
                    <td>
                        <img style="width:80px;height:30px" src="/${b.idcardup}"/>
                    </td>
                    <td>
                        <img style="width:80px;height:30px" src="/${b.idcarddown}"/>
                    </td>
                    <td>${b.postid}</td>
                    <td>${b.department.dname}</td>
                    <td>
                        <form action="/employee/emp1/${b.eid}.do" method="post" style="height:8px;">
                            <input type="submit" value="修改"/>
                        </form>
                    </td>
                    <td>
                        <form action="/employee/emp/${b.eid}.do" method="post" style="height:8px;">
                            <input type="hidden" name="_method" value="DELETE"/>
                            <input type="submit" value="删除"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</center>
</body>
</html>
