package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class userRestController {
    @Autowired
    userService userService;
    /*@GetMapping("/jj")
    //public Collection<User> showUsers(){
        return users.values();
    }*/

/*    @GetMapping("/jiuji")
    public ResponseEntity<User> loginUser(@RequestParam String username){
        long id = usernames.get(username);
        User u = users.get(id);
        if(u!=null){
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User user){
        userService.createUser(user);
        return user;
    }
}
