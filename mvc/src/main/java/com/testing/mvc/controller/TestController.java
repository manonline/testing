package com.testing.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(@RequestParam(value = "var1", required = false) String var1,
                       @RequestParam(value = "var2", required = false) String var2,
                       @RequestParam(value = "var3", required = false) String var3,
                       Model model) {
        // get input
        model.addAttribute("method", "test");
        model.addAttribute("arguments","var1=" + var1 + ";"
                + "var2=" + var2 + ";"
                + "var3=" + var3);
        // process data
        // return a view
        return "test";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView requestParamTest(@RequestParam(value = "var1") String var1,
                                         @RequestParam(value = "var2") String var2,
                                         @RequestParam(value = "var2") String var3,
                                         RedirectAttributes attributes) {
        // get the input

        // process the data

        // return -
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("method", "requestParamTest");
        mv.addObject("arguments", "var1=" + var1 + ";"
                + "var2=" + var2 + ";"
                + "var3=" + var3);

        return mv;
    }

}

