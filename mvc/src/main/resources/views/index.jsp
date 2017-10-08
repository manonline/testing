<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>Welcome</title>
</head>
<body>

<h1>Welcome to Spring MVC world</h1>
${message}
<br>
<br>
Please Login:
<br>
<br>
<form action="/login" method="post">
    <input type="text"      name="user" placeholder="用户名">
    <input type="password"  name="password" placeholder="密码">
    <input type="submit" value="登录">
</form>

</body>
</html>