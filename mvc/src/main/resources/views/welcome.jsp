<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>Welcome</title>
</head>
<body>

<h1>Login Succeed</h1>
<br>
<br>
Information Submitted:
<br>
${user}
${password}

<br>
System Information:
<br>
${servertime}
${serverinstance}

Pleae Fil in Additional Information:

<form action="/welcome/moreinfo" method="post">
    <input type="hidden" name="user.name" value=${user} placeholder="用户名">
    <input type="text"   name="user.dob" placeholder="生日">
    <input type="text"   name="user.mobile" placeholder="手机">
    <input type="submit" value="登录">
</form>

</body>
</html>