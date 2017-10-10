package com.testing.mvc.controller;

import com.testing.mvc.controller.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    /**
     *  test home page
     */
    @RequestMapping()
    public String testHome() {
        return "test";
    }

    /**
     * argument testing
     */
    // path variables
    @RequestMapping(value = "/vartesting/{var1}/{var2}/{var3}")
    public String pathVarTest(@PathVariable(value = "var1", required = false) String var1,
                              @PathVariable(value = "var2", required = false) String var2,
                              @PathVariable(value = "var3", required = false) String var3,
                              Model model) {
        // get input

        // process data and populate model
        model.addAttribute("method", "pathVarTest");
        model.addAttribute("arguments","var1=" + var1 + ";"
                + "var2=" + var2 + ";"
                + "var3=" + var3);

        // return a view
        return "test";
    }

    // query string
    @RequestMapping(value = "/vartesting", method = RequestMethod.GET)
    public String queryStringTest(@RequestParam(value = "var1", required = false) String var1,
                                  @RequestParam(value = "var2", required = false) String var2,
                                  @RequestParam(value = "var3", required = false) String var3,
                                  Model model) {
        // get input
        model.addAttribute("method", "queryStringTest");
        model.addAttribute("arguments","var1=" + var1 + ";"
                + "var2=" + var2 + ";"
                + "var3=" + var3);
        // process data and populate model
        // return a view
        return "test";
    }

    // request body
    @RequestMapping(value = "/vartesting", method = RequestMethod.POST)
    public ModelAndView requestParamTest(@RequestParam(value = "var1") String var1,
                                         @RequestParam(value = "var2") String var2,
                                         @RequestParam(value = "var2") String var3) {
        // get the input

        // process the data

        // return a view
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("method", "requestParamTest");
        mv.addObject("arguments", "var1=" + var1 + ";"
                + "var2=" + var2 + ";"
                + "var3=" + var3);

        return mv;
    }

    /**
     * arguments using @PathArgument Map and @RequestParam Map
     */
    // path variables
    @RequestMapping(value = "/util/vartesting/{var1}/{var2}/{var3}")
    public String pathVarsTest(@PathVariable Map<String, String> variables, Model model) {
        // get input

        // process data and populate model
        model.addAttribute("method", "pathVarUtilTest");
        model.addAttribute("arguments","var1=" + variables.get("var1") + ";"
                + "var2=" + variables.get("var2") + ";"
                + "var3=" + variables.get("var3"));

        // return a view
        return "test";
    }

    @RequestMapping(value = "/util/vartesting", method = RequestMethod.GET)
    public String queryStringTest(@RequestParam Map<String, String> params, Model model) {
        // get input
        model.addAttribute("method", "queryStringUtilTest");
        model.addAttribute("arguments","var1=" + params.get("var1") + ";"
                + "var2=" + params.get("var2") + ";"
                + "var3=" + params.get("var3"));
        // process data and populate model
        // return a view
        return "test";
    }

    @RequestMapping(value = "/util/vartesting", method = RequestMethod.POST)
    public ModelAndView requestParamTest(@RequestParam Map<String, String> params) {
        // get the input

        // process the data

        // return a view
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("method", "requestParamUtilTest");
        mv.addObject("arguments", "var1=" + params.get("var1") + ";"
                + "var2=" + params.get("var2") + ";"
                + "var3=" + params.get("var3"));

        return mv;
    }

    /**
     * arguments using @MethodAttributes
     */
    // path variables
    @RequestMapping(value = "/attributes/vartesting/{var1}/{var2}/{var3}")
    public String pathVarsAttributeTest(@ModelAttribute("var1") String var1,
                                        @ModelAttribute("var2") String var2,
                                        @ModelAttribute("var3") String var3,
                                        Model model) {
        // get input

        // process data and populate model
        model.addAttribute("method", "pathVarAttributeTest");
        // var1, var2, var3 are already in the model

        // return a view
        return "test";
    }

    @RequestMapping(value = "/attributes/vartesting", method = RequestMethod.GET)
    public String queryStringAttributeTest(@ModelAttribute("var1") String var1,
                                           @ModelAttribute("var2") String var2,
                                           @ModelAttribute("var3") String var3,
                                           Model model) {
        // get input
        model.addAttribute("method", "queryStringAttributeTest");
        // var1, var2, var3 are already in the model

        // process data and populate model
        // return a view
        return "test";
    }

    @RequestMapping(value = "/attributes/vartesting", method = RequestMethod.POST)
    public ModelAndView requestParamAttributeTest(@ModelAttribute("var1") String var1,
                                                  @ModelAttribute("var2") String var2,
                                                  @ModelAttribute("var3") String var3) {
        // get the input

        // process the data

        // return a view
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("method", "requestParamAttributeTest");

        return mv;
    }

    /**
     * ModelAttributes
     */
    @ModelAttribute
    public void populateModelVoid(Model model) {
        model.addAttribute("SystemTime", System.currentTimeMillis());
        model.addAttribute("SystemDate", Calendar.DATE);
    }

    @ModelAttribute
    public String populdateModelString() {
        return "This is the String populated by @ModelAttribute";
    }

    @ModelAttribute
    public User populdateModelObject() {
        User user = new User();
        user.setName("Tester");
        return user;
    }

    /**
     * Exception Handler
     */
    @RequestMapping(value = "/errortest", method = RequestMethod.POST)
    public String errorTest(@RequestParam("var1") Integer var1,
                            @RequestParam("var2") Integer var2,
                            Model model) {
        // get input
        Integer result;
        try {
            result = var1 / var2;
        } catch (ArithmeticException ex) {
            model.addAttribute("ErrorMessage", "Exception Caught in Controller - Exception Type=" + ex.getClass().getSimpleName());
            return "test";
        }

        System.out.println(var1);
        System.out.println(var2);

        // handle request
        model.addAttribute("result", result);

        // return result
        return "test";
    }

    @ExceptionHandler({ArithmeticException.class,
                       NullPointerException.class})
    public String handleUserExists(Exception exception, Model model) {
        model.addAttribute("ErrorMessage", "Exception Caught in Controller - Exception Type=" + exception.getClass().getSimpleName());        return "test";
    }
}

