package com.myapp.clientservice.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Custom error controller for client-service app.
 *
 * @author Ivan_Semenov
 */
@Slf4j
@Controller
public class ClientServiceErrorController implements ErrorController {

    /**
     * The default directory used by Spring Boot is resources/templates/.
     *
     * @param request incoming request
     * @return error page template by name
     * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-web-applications.html#boot-features-error-handling-custom-error-pages</a>
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
            Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
            log.error("An error with status code {} occurred: {} - {}", status, exception, message);
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error500";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
