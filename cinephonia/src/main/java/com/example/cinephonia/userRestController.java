package com.example.cinephonia;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class userRestController {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();
    private Map<String, Long> usernames = new ConcurrentHashMap<>();
    @GetMapping("/jj")
    public Collection<User> showUsers(){
        return users.values();
    }

    @GetMapping("/jiuji")
    public ResponseEntity<User> loginUser(@RequestParam String username){
        long id = usernames.get(username);
        User u = users.get(id);
        if(u!=null){
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User user){
        long id = lastid.incrementAndGet();
        user.setId(id);
        users.put(id,user);
        return user;
    }
}
