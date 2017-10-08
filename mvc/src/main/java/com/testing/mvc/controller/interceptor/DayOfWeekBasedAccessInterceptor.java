package com.testing.mvc.controller.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/*<mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="urlxxxx"/>
            <bean class = "...."
        </mvc:interceptor>
</mvc:interceptors>*/
public class DayOfWeekBasedAccessInterceptor extends HandlerInterceptorAdapter {

    // BEFORE HANDLER : get called before Spring MVC handling the request; need to be registered;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Calendar cal = Calendar.getInstance();

        int dayOfWeek = cal.get(cal.DAY_OF_WEEK);

        if (dayOfWeek == 1) {
            response.getWriter().write("The Website is closed on Sunday; Please try accessing it on any week day");
            return false;
        }

        return true;
    }

    // AFTER HANDLER : get called after Spring MVC executes the request handler method for the request
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("AFTER HANDLER : postHandle()");
    }

    // AFTER VIEW : get called after the response object is produced by the view for the request
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("AFTER VIEW : afterCompletion()");
    }
}