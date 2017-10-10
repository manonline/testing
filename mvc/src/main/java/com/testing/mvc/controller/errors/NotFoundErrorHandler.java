package com.testing.mvc.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by davidqi on 10/10/17.
 */
@Controller
public class NotFoundErrorHandler {
    /**
     * Catch Any unspecific request (wrong URL)
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    // to catch any unmatched request
    @RequestMapping("/**")
    public String notFound(Model model) {
        model.addAttribute("errorMessage", "Cannot find the requested page");
        return "error";
    }
}

/**
 * Not Found Handling
 * 1. "/notFound" Block WebServer Calls to "/notFound" - foe example, Tomcat anyway calls to /NotFound
 * ----
 * @Override
 * protected void noHandlerFound(HttpServletRequest request,
 *     HttpServletResponse response) throws Exception {
 *     response.sendRedirect(request.getContextPath() + "/notFound");
 * }
 * ----
 * @Controller
 * @RequestMapping("/notFound")
 * public String notFoundHandler() {...}
 *
 * 2. "/**" Match any unspecific request - specific request will be handled by specific handler
 * @Controller
 * @RequestMapping("/**")
 * public String notFoundHandler() {...}
 *
 * 3. Override WebServer error-page in web.xml
 * <error-page>
 *     <error-code>404</error-code>
 *     <localtion>/resource/view/404.html</localtion>
 * </error-page>
 */