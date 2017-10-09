package com.testing.mvc.controller.sample.sevlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CookieWrite extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            // create a cookie item
            Cookie myCookie = new Cookie("color1", "red");
            // set cookie durable property, default not store;
            myCookie.setMaxAge(30000);
            // response header: add cookie
            res.addCookie(myCookie);
            // response header: other items
            res.setContentType("text/html;charset=gbk");

            // response body
            PrintWriter pw = res.getWriter();
            pw.println("已经创建了cookie");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
