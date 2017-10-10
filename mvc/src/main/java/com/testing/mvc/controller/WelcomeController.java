package com.testing.mvc.controller;

import com.testing.mvc.controller.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
        mv.addObject("welcomeMsg", welcomeMsg);
        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session) {

        // return to the home page
        ModelAndView mv = new ModelAndView("redirect:/index");

        // invalidate session
        session.invalidate();
        return mv;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ModelAndView moreinfo(@ModelAttribute("user") User user) {
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

    /**
     * @ModelAttribute
     * public void addingCommandObject(Model model) {
     *     model.addAttribute("servertime", System.currentTimeMillis());
     *     model.addAttribute("serverinstance", System.getProperty("serverInstance"));
     * }
     */
}