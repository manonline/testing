package com.testing.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("manager")
public class SessionExampleController {

/*
    @Resource
    private ManagerService managerServiceImpl;

    @RequestMapping(value = "manager/login.do", method = RequestMethod.GET)
    public ModelAndView login(ManagerModel managerModel, ModelMap model) {

        ManagerModel manager = managerServiceImpl.getManager(managerModel);
        if (manager != null) {
            manager.setPassword("");
            model.addAttribute("manager", manager);
            return new ModelAndView(new RedirectView("../admin/main.jsp"));
        } else {
            return new ModelAndView(new RedirectView("../admin/login.jsp"));
        }
    }

    @RequestMapping(value = "manager/logout.do", method = RequestMethod.GET)
    public String logout(@ModelAttribute("manager") ManagerModel managerModel, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "success";
    }

    @Resource
    private ManagerService managerServiceImpl;

    @RequestMapping(value = "manager/login.do", method = RequestMethod.GET)
    public ModelAndView login(ManagerModel managerModel, HttpSession httpSession) {

        ManagerModel manager = managerServiceImpl.getManager(managerModel);
        if (manager != null) {
            manager.setPassword("");
            httpSession.setAttribute("manager", manager);
            return new ModelAndView(new RedirectView("../admin/main.jsp"));
        } else {
            return new ModelAndView(new RedirectView("../admin/login.jsp"));
        }
    }

    @RequestMapping(value = "manager/logout.do", method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        httpSession.
        httpSession.getAttribute("manager");
        return "success";
    }
    */
}

/**
 *
 *
 *
 *
 *
 *
 *
 */
