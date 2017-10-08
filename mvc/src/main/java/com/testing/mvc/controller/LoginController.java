package com.testing.mvc.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("/login")
public class LoginController {

    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final String LOGIN_ERROR = "Login Failed";

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam(value = "user") String userName,
                               @RequestParam(value = "password") String password) {
        // get the input
        if (password.length() <= MIN_PASSWORD_LENGTH) {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("error", LOGIN_ERROR);
            mv.addObject("errorDescription", "password should be greater than 6");
            return mv;
        }
        // process the data

        // return
        ModelAndView mv = new ModelAndView("welcome");
        mv.addObject("user", userName);
        mv.addObject("password", password);
        return mv;
    }

}
