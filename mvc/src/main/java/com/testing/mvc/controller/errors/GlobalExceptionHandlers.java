package com.testing.mvc.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlers {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String handleUnknownException(Exception exception, Model model) {
        // get neccessary information

        // pass to model
        model.addAttribute("errorMessage",
                "Exception Occurred - Exception Type=" + exception.getClass().getSimpleName());

        // pass to error page
        return "error";
    }
}

/** Equivalent XML Configuration
 * <bean id="simpleMappingExceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
 *     <property name="exceptionMappings">
 *         <map>
 *             <entry key="NullPointerException" value="NullPointerException"/>
 *             <entry key="ExceptionClassName" value="ViewName" />
 *         </map>
 *     </property>
 * </bean>
 */
