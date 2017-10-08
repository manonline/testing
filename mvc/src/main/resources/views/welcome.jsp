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
${welcomeMsg}<br>

<h2>Information Submitted:</h2>
用户名: ${user}<br>
密码  : ${password}

<h2>System Information:</h2>
${servertime}<br>
${serverinstance}

<h2>Submit More Info:</h2>

<form action="/welcome/moreinfo" method="post">
    <input type="text"   name="user.dob" placeholder="生日"><br>
    <input type="text"   name="user.mobile" placeholder="手机"><br>
    <input type="submit" value="Confirm">
    <input type="reset"  value="Cancel">
    <input type="hidden" name="user.name" value=${user} placeholder="用户名">
</form>

</body>
</html>