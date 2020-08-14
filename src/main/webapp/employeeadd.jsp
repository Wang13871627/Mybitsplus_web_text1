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
    <title>添加员工</title>
</head>
<body>
<center>
<h2><font color="blue">添加员工</font></h2>
    <form action="/employee/emp.do" method="post" enctype="multipart/form-data">
        姓名：<input type="text" name="name"/><br>
        年龄：<input type="text" name="age"/><br>
        性别：
        男 <input type="radio" value="男" checked name="sex"/>
        女 <input type="radio" value="女" name="sex"/><br>
        爱好：
        看书 <input type="checkbox" value="看书" name="hobby"/>
        打篮球 <input type="checkbox" value="打篮球" name="hobby"/>
        跑步 <input type="checkbox" value="跑步" name="hobby"/>
        打游戏 <input type="checkbox" value="打游戏" name="hobby"/><br>
        身份证正：<input type="file" name="up"/><br>
        身份证反：<input type="file" name="down"/><br>
        职位：<c:forEach items="${list1}" var="a">
                ${a.pname}<input type="checkbox" value="${a.pid}" name="postid"/>
            </c:forEach>
        <br>
        部门：<select name="deptid">
                <c:forEach items="${list}" var="b">
                    <option value="${b.did}">${b.dname}</option>
                </c:forEach>
            </select><br>
        <input type="submit" style="background-color: deeppink" value="添加"/>
    </form>
</center>
</body>
</html>
