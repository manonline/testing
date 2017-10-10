<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
<h1>Test Result </h1>
<table border="1" width="1500" >
    <tr>
        <td>
            <h2>From Request</h2>
            Handler Invoked: ${method}<br>
            Argument Passed: ${arguments}<br>
            Model Result: ${var1}, ${var2}, ${var3}<br>
        </td>
        <td>
            <h2>Model Attributes</h2>
            Void : System Time: ${SystemTime}<br>
            Void : System Date: ${SystemDate}<br>
            String: ${string}<br>
            Object: User.name: ${user.name}<br>
            Object: private User.dob: ${user.dob}<br>
            Object: private User.mobile: ${user.mobile}<br>
            Object: private User.hobbie: ${user.hobbie}
        </td>
        <td>
            <h2>Error Exceptions</h2>
            Exception Type:${ErrorMessage}<br>
            <form action="/test/errortest" method="post">
                <input type="text" name="var1" placeholder="var1"> divide by
                <input type="text" name="var2" placeholder="var2"> = ${result}
                <br>
                <input type="submit" value="Submit">
            </form>
        </td>
    </tr>
</table>

<br>

<table border="1">
    <tr>
        <td>
            <h2>Plain Method</h2>

            <h3>Path Variable</h3>
            <a href="http://localhost:8080/test/vartesting/p1/p2/p3" name="Submit">Submit</a>

            <h3>Request Parameters - Query String</h3>
            <form action="/test/vartesting" method="get">
                <input type="text" name="var1" placeholder="var1">
                <input type="text" name="var2" placeholder="var2">
                <input type="text" name="var3" placeholder="var3">
                <input type="submit" value="Submit">
            </form>

            <h3>Request Parameters - Form</h3>
            <form action="/test/vartesting" method="post">
                <input type="text" name="var1" placeholder="var1">
                <input type="text" name="var2" placeholder="var2">
                <input type="text" name="var3" placeholder="var3">
                <input type="submit" value="Submit">
            </form><br>
        </td>
        <td>
            <h2>Map Util</h2>
            <h3>Path Variable</h3>
            <a href="http://localhost:8080/test/util/vartesting/p1/p2/p3" name="Submit">Submit</a>

            <h3>Request Parameters - Query String</h3>
            <form action="/test/util/vartesting" method="get">
                <input type="text" name="var1" placeholder="var1">
                <input type="text" name="var2" placeholder="var2">
                <input type="text" name="var3" placeholder="var3">
                <input type="submit" value="Submit">
            </form>

            <h3>Request Parameters - Form</h3>
            <form action="/test/util/vartesting" method="post">
                <input type="text" name="var1" placeholder="var1">
                <input type="text" name="var2" placeholder="var2">
                <input type="text" name="var3" placeholder="var3">
                <input type="submit" value="Submit">
            </form><br>
        </td>
        <td>
            <h2>Model Attributes</h2>
            <h3>Path Variable</h3>
            <a href="http://localhost:8080/test/attributes/vartesting/p1/p2/p3" name="Submit">Submit</a>

            <h3>Request Parameters - Query String</h3>
            <form action="/test/attributes/vartesting" method="get">
                <input type="text" name="var1" placeholder="var1">
                <input type="text" name="var2" placeholder="var2">
                <input type="text" name="var3" placeholder="var3">
                <input type="submit" value="Submit">
            </form>

            <h3>Request Parameters - Form</h3>
            <form action="/test/attributes/vartesting" method="post">
                <input type="text" name="var1" placeholder="var1">
                <input type="text" name="var2" placeholder="var2">
                <input type="text" name="var3" placeholder="var3">
                <input type="submit" value="Submit">
            </form><br>
        </td>
    </tr>
</table>

</body>
</html>