package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class userRESTController {
    @Autowired
    userService userService;

   @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        User user = userService.getUserById(id);
        if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/users")
    public Collection<User> showUsers(){
        return userService.userList();
    }
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User user){
        userService.createUser(user);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        User user=userService.removeUser(id);
        if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> putUser(@PathVariable long id, @RequestBody User us){
        User user = userService.getUserById(id);
        if(user!=null){
            userService.putUser(us,id);
            return new ResponseEntity<>(us,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
