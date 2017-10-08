package com.testing.mvc.controller;

import com.testing.mvc.controller.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.Path;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    private static final String welcomeMsg = "Hello, Welcome to this site";

    @RequestMapping
    public ModelAndView welcome() {
        // retrieve input data

        // process data

        // populate model and return view
        ModelAndView mv = new ModelAndView("welcome");
        mv.addObject("welcome.message", welcomeMsg);

        return mv;
    }

    @RequestMapping(value = "/moreinfo", method = RequestMethod.POST)
    public ModelAndView welcomeName(@ModelAttribute("user") User user) {
        // retrieve input data
        System.out.println(user.getName());
        System.out.println(user.getDob());
        System.out.println(user.getMobile());

        // process data

        // populate model and return view
        ModelAndView mv = new ModelAndView("userdetail");
        mv.addObject("welcome.message", welcomeMsg);

        return mv;
    }

    @ModelAttribute
    public void addingCommandObject(Model model) {
        model.addAttribute("servertime", System.currentTimeMillis());
        model.addAttribute("serverinstance", System.getProperty("serverInstance"));
    }
}
