<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
    <h1>Request Parameter Testing</h1>

    <form action="/login" method="post">
        <input type="text" name="var1" placeholder="var1">
        <input type="text" name="var2" placeholder="var2">
        <input type="text" name="var3" placeholder="var3">
        <input type="submit" value="提交">
    </form>
</body>
</html>