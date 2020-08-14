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
    <title>修改员工</title>
</head>
<body>
<center>
<h2><font color="blue">修改员工</font></h2>
    <form action="/employee/emp.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_method" value="PUT"/>
        <input type="hidden" name="eid" value="${emp.eid}"/>
        姓名：<input type="text" name="name" value="${emp.name}"/><br>
        年龄：<input type="text" name="age" value="${emp.age}"/><br>
        性别：<c:if test="${emp.sex=='男'}">
        男 <input type="radio" value="男" checked name="sex"/>
        女 <input type="radio" value="女" name="sex"/><br>
            </c:if>
        <c:if test="${emp.sex!='男'}">
            男 <input type="radio" value="男" name="sex"/>
            女 <input type="radio" value="女" checked name="sex"/><br>
        </c:if>
        爱好：
        <c:forEach items="${list2}" var="c">
            <c:if test="${emp.hobby.contains(c)}">
                ${c}<input type="checkbox" checked value="${c}" name="hobby"/>
            </c:if>
            <c:if test="${!emp.hobby.contains(c)}">
                ${c}<input type="checkbox" value="${c}" name="hobby"/>
            </c:if>
        </c:forEach><br>
        <img style="width:80px;height:30px" src="/${emp.idcardup}"/><br>
        身份证正：<input type="file" name="up"/><br>
        <img style="width:80px;height:30px" src="/${emp.idcarddown}"/><br>
        身份证反：<input type="file" name="down"/><br>
        职位：
        <c:forEach items="${list1}" var="a">
            <c:if test="${emp.postid.contains(a.pname)}">
                ${a.pname}<input type="checkbox" checked value="${a.pid}" name="postid"/>
            </c:if>
            <c:if test="${!emp.postid.contains(a.pname)}">
                ${a.pname}<input type="checkbox" value="${a.pid}" name="postid"/>
            </c:if>
        </c:forEach><br>
        部门：<select name="deptid">
                <c:forEach items="${list}" var="b">
                    <c:if test="${emp.deptid==b.did}">
                        <option value="${b.did}" selected>${b.dname}</option>
                    </c:if>
                    <c:if test="${emp.deptid!=b.did}">
                        <option value="${b.did}">${b.dname}</option>
                    </c:if>
                </c:forEach>
            </select><br>
        <input type="submit" style="background-color: deeppink" value="修改"/>
    </form>
</center>
</body>
</html>
