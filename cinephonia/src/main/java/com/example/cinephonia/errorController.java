package com.example.cinephonia;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class errorController {
    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
