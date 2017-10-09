package com.testing.mvc.controller.sample.sevlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CookieRead extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            // request header
            res.setContentType("text/html;charset=gbk");

            // response body - print out cookie item stored earlier
            PrintWriter pw = res.getWriter();
            int i = 0;
            // read cookies from client request
            Cookie[] allCookies = req.getCookies();
            if (allCookies != null) {
                // loop through all cookie items
                for (i = 0; i < allCookies.length; i++) {
                    Cookie temp = allCookies[i];
                    // get specific cookie item
                    if (temp.getName().equals("color1")) {
                        String val = temp.getValue();
                        pw.println("color1=" + val);
                        break;
                    }
                }
                // if not found, it must be expired thus not included in the request
                if (allCookies.length == i) {
                    pw.println("cookie 过期");
                }
            } else {
                pw.println("不存在color1这个cookie/或是过期了!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}