package com.app.casher.controllers;

import com.app.casher.exceptions.ExceptionMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({ExceptionMessage.class})
    public String exceptionHandler(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errorPage";
    }

}
