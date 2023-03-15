package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class userController {

    @Autowired
    userService userService;
    @GetMapping("/register")
    public String resgisterButton(){
        return "register";
    }

    @GetMapping("/films")
    public String filmsSection(){
        return "films";
    }

    @GetMapping("/songs")
    public String songsSection(){
        return "songs";
    }

    @GetMapping("/login")
    public String loginButton(){
        return "login";
    }


    @PostMapping("/register/newUser")
    public String newUser(Model model, User user){
        userService.createUser(user);
        return "newuser";
    }
}
