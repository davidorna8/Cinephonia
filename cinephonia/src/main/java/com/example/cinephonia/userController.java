package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
@RequestMapping("/api/")
@Controller
public class userController {
    private static String regionList[]={"North America","Caribbean","South America","Asia","East Asia","Oceania","Middle East",
                                        "Africa","Western Europe","Eastern Europe"};
    @Autowired
    userService userService;
    @GetMapping("/register")
    public String resgisterButton(Model model){
        List<String> regList= Arrays.asList(regionList);
        model.addAttribute("regionList",regList);
        return "register";
    }

    @GetMapping("/login")
    public String loginButton(){
        return "login";
    }


    @PostMapping("/register/newuser")
    public String newUser(Model model, User user){
        userService.createUser(user);
        model.addAttribute("username",user.getUsername());
        return "newuser";
    }
}