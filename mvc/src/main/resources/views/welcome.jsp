<%@ page import="java.util.Enumeration" %>
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
<form action="/welcome/logout" method="post">
    <input type="submit" value="Logout">
</form>

<h2>Information Submitted:</h2>
用户名: ${user}<br>
密码  : ${password}

<h2>Server Session Information:</h2>
Session Id: <%= session.getId()%><br>
Session isNew: <%= session.isNew()%><br>
Session Created On: <%= session.getCreationTime()%><br>
Session Max Inactive Interval: <%= session.getMaxInactiveInterval()%><br>
Session Last Accessed At: <%= session.getLastAccessedTime()%><br>
Session # of Accesses:
<%
    Integer counter;
    counter = (Integer) session.getAttribute("accesses");
    if (counter == null) {
        counter = new Integer(1);
    } else {
        counter++;
    }
    session.setAttribute("accesses", counter);
%>
<%= counter%><br>
Session Attributes: <br>
<%
    String attributeName;
    Enumeration itr = session.getAttributeNames();
    while (itr.hasMoreElements()) {
        attributeName = itr.nextElement().toString();
        out.println("   Attribute Name:" + attributeName);
        out.println("   Attribute Value:" + session.getAttribute(attributeName));
    }
%>

<h2>Request Cookie Information:</h2>
<%
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
        out.println("No Cookie");
    } else {
        for (int i = 0; i < cookies.length; i++) {
%>
            Cookie Name: <%= cookies[i].getName()%><br>
            Cookie Value: <%= cookies[i].getValue()%><br>
            Max Age (in S): <%= cookies[i].getMaxAge()%><br>
<%
        }
    }
%>

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