package com.example.cinephonia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class mainController {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();
    private Map<String, Long> usernames = new ConcurrentHashMap<>();

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


    @PostMapping("/register/newuser/{username}")
    public String newUser(Model model, User user){
        long id = lastid.incrementAndGet();
        user.setId(id);
        users.put(id,user);
        return "newuser";
    }
}
