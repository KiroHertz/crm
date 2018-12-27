package com.jlopez.crmapi.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController()
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgumentException(IllegalArgumentException ex, HttpServletResponse res) throws IOException {
        res.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }

}