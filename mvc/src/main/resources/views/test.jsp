<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
    <h1>Test Result </h1>
    Handler Invoked: ${method}<br>
    Argument Passed: ${arguments}

    <h2>Submit your Arguments</h2>
    <h3>Submit Arguments via Path Variables</h3>
    <a href="http://localhost:8080/test?var1=var1&var2=var2&var3=var3" name="Submit">Submit</a>

    <h3>Submit Arguments via Request Parameters</h3>
    <form action="/test" method="post">
        <input type="text" name="var1" placeholder="var1">
        <input type="text" name="var2" placeholder="var2">
        <input type="text" name="var3" placeholder="var3">
        <input type="submit" value="提交">
    </form>


</body>
</html>