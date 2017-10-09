package com.testing.mvc.controller.sample.sevlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CookieDelete extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            // request header
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();

            int i = 0;
            // read cookies from client request
            Cookie[] allCookies = req.getCookies();
            if (allCookies != null) {
                // loop through all cookie items
                for (i = 0; i < allCookies.length; i++) {
                    Cookie temp = allCookies[i];
                    // set specific cookie item
                    if (temp.getName().equals("color")) {
                        // set to zero to remove (expire immediately
                        temp.setMaxAge(0);
                        pw.println("删除了color这个cookie");
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}