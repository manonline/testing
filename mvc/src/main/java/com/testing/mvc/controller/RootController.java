package com.testing.mvc.controller;

import com.testing.mvc.controller.vo.User;
import com.testing.mvc.controller.vo.binding.CustomNameEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/")
public class RootController {

    /**
     * Basic Controller Mapping
     * Return String -> ViewResolver -> View -> JSP -> HTML
     */
    @RequestMapping
    public String rootPage() {
        return "index";
    }

    @RequestMapping(value = {"/home", "/index"})
    public String homePage() {
        return "index";
    }

    /**
     * Input from @PathVariable
     * Return ModelAndView
     */
    @RequestMapping(value ={"/pathVar/{var1}/{var2}"})
    public ModelAndView pathVarTesting(@PathVariable("var1") String var1, @PathVariable("var2") String var2) {
        // get the input

        // process the input

        // populate the model and return
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message",
                "Argument Retrived from PathVariable : var1=" + var1 + " var2=" + var2);
        return mv;
    }

    @RequestMapping(value ={"/pathVar/{var1}/{var2}/{var3}"})
    public ModelAndView pathVarTesting2(@PathVariable Map<String, String> arguments) {
        // get the input

        // process the input

        // populate the model and return
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "Argument Retrived from PathVariable : " +
                "var1=" + arguments.get("var1") + " var2=" + arguments.get("var1") + " var3=" + arguments.get("var3"));
        return mv;
    }

    /**
     * Input from @RequestParam
     * Return ModelAndView
     */
    @RequestMapping(value = "/reqParam", method = RequestMethod.GET)
    public ModelAndView reqParamTesting(@RequestParam(value = "var1", defaultValue = "var1") String var1,
                                        @RequestParam(value = "var2", defaultValue = "var2") String var2,
                                        @RequestParam(value = "var3", defaultValue = "var3") String var3) {

        // get parameters

        // processing

        // return response - view (-> jsp -> html)
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "Argument Retrived from Request : " +
                "var1=" + var1 + " var2=" + var2 + " var3=" + var3);

        return mv;
    }

    /**
     * Input populated into @ModelAttribute
     * Return ModelAndView
     */
    @RequestMapping(value = "/modelAndView", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@ModelAttribute("user") User user) {

        // get parameters - skipped due to using @ModelAttribute auto-binding
        // User user = new User();
        // user.setName(reqPar.get("userName"));
        // user.setHobby(reqPar.get("hobby");

        // processing

        // return response - view (->jsp -> html)
        ModelAndView model = new ModelAndView("AdmissionSuccess");
        model.addObject("headerMessage", "Welcome To Our Example");
        // model1.addObject("user1", user1);

        return model;
    }

    /**
     * Binding Result: handling
     */
    @RequestMapping(value = "/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("user1") User user, BindingResult result) {

        // handling errors
        if (result.hasErrors()) {
            ModelAndView model = new ModelAndView("AdmissionForm");
            return model;
        }

        // get parameters - skipped due to using @ModelAttribute auto-binding
        // User user1 = new User();
        // user.setName(reqPar.get("userName"));
        // user.setHobby(reqPar.get("hobby");

        // processing

        // return response - view (->jsp -> html)
        ModelAndView model = new ModelAndView("AdmissionSuccess");
        model.addObject("headerMessage", "Welcome To Our Example");
        // model.addObject("user", user);

        return model;
    }

    /**
     * Adding Variables :
     */
    @ModelAttribute
    public void addingCommandObject(Model model) {
        model.addAttribute("message1", "message1 from ModelAttribute");
        model.addAttribute("message2", "message2 from ModelAttribute");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // disable auto-binding on selected fields
        binder.setDisallowedFields(new String[] {"mobile"});

        // bind fields manually
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy****MM****dd");
        binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class, "name", new CustomNameEditor());
    }

    /**
     * Exception Handler
     */
    @RequestMapping(value = "/unhanldedException", method = RequestMethod.POST)
    public ModelAndView unhandledException() throws Exception {

        String exceptionOccured = "NULL_POINTER";

        if (exceptionOccured.equalsIgnoreCase("NULL_POINTER")) {
            throw new IllegalArgumentException("Null Pointer Exception");
        }

        ModelAndView model = new ModelAndView("AdmissionForm");
        return model;
    }

    // specific exception handler
    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerException() {
        return "NullPointerException";
    }

    // generic exception handler
    @ExceptionHandler(value = Exception.class)
    public String handleExcpetion() {
        return "Exception";
    }
}
