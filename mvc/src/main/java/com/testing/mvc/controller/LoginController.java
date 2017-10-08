package com.testing.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("/login")
public class LoginController {

    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final String LOGIN_ERROR = "Login Failed";

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam(value = "user") String userName,
                               @RequestParam(value = "password") String password,
                               RedirectAttributes attributes) {
        // get the input
        if (password.length() <= MIN_PASSWORD_LENGTH) {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("error", LOGIN_ERROR);
            mv.addObject("errorDescription", "password should be greater than 6");
            return mv;
        }
        // process the data

        // return - redirect to welcome
        // otherwise URL stay same and preventing backward/refresh working wrong
        ModelAndView mv = new ModelAndView("redirect:/welcome");
        attributes.addFlashAttribute("user", userName);
        attributes.addFlashAttribute("password", password);
        return mv;
    }

/**
 * Redirect
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView index(HttpServletResponse response) {
        ModelAndView model = new ModelAndView("/home/index");
        return model;
    }

    // 1.1 redirect via HttpServletResponse
    @RequestMapping(value = "/toIndex", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toIndex(HttpServletResponse response) {
        try {
            response.sendRedirect("/index");
        } catch (IOException e1) {
        }
        return null;
    }

    // 1.2 redirect via HttpServletResponse with arguments - URL
    @RequestMapping(value = "/toIndex", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toIndex(HttpServletResponse response) {
        try {
            response.sendRedirect("/index?arg1=" + arg1 ";arg2=" + arg2);
        } catch (IOException e1) {
        }
        return null;
    }

    // 2.1 redirect via SpringMVC - ViewResolver
    @RequestMapping(value="/toIndex",method = { RequestMethod.POST, RequestMethod.GET })
    public  String toIndex(HttpServletResponse response){
        return "redirect:/index";
    }

    // 2.2 redirect via SpringMVC - ViewResolver + arguments - URL
    @RequestMapping(value="/toIndex",method = { RequestMethod.POST, RequestMethod.GET })
    public  String toIndex(HttpServletResponse response){
        return "/index?arg1=" + arg1 ";arg2=" + arg2;
    }

    // 2.3 redirect via SpringMVC - ViewResolver + arguments - URL
    @RequestMapping(value="/toIndex",method = { RequestMethod.POST, RequestMethod.GET })
    public  String toIndex(HttpServletResponse response){
        model.addAttribute("arg1", arg1);
        model.addAttribute("arg2", arg2);
        return "/index";
    }

    // 2.4 redirect via SpringMVC - ViewResolver + arguments via RedirectAttributes
    @RequestMapping(value="/toIndex",method = { RequestMethod.POST, RequestMethod.GET })
    public  String toIndex(HttpServletResponse response,RedirectAttributes model){
        model.addFlashAttribute("userName", 'TimerBin');
        model.addFlashAttribute("userPass", 'ApeVm23U3wxEGocX');
        return "redirect:/index";
    }
*/
}

