package com.example.cinephonia.RESTControllers;

import com.example.cinephonia.Models.Song;
import com.example.cinephonia.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RequestMapping("/api/")
@RestController
public class userRESTController {
    @Autowired
    com.example.cinephonia.Services.userService userService;
   @GetMapping("/users/{id}") // get user by Id
    public ResponseEntity<User> getUser(@PathVariable long id){
        Optional<User> optionalUser=userService.getOptional(id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            return new ResponseEntity<>(user, HttpStatus.OK); // if it is found, return OK
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);// if not found, return NOT FOUND
        }
    }
    @GetMapping("/users") // full user list
    public Collection<User> showUsers(){
        return userService.userList();
    }
    @PostMapping("/users")// create a user
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User user){
        userService.createUser(user);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        Optional<User> optionalUser=userService.getOptional(); //remove the user from the map
        if(optionalUser.isPresent()){ // if it was found
            User user=optionalUser.get();
            userService.removeUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> putUser(@PathVariable long id, @RequestBody User us){
        Optional<User> optionalUser = userService.getOptional(id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            userService.putUser(us,id);
            return new ResponseEntity<>(us,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
