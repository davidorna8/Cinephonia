package com.example.cinephonia.Controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class errorController { // Controller for the error page
    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
