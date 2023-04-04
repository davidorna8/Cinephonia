package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class userController {
    private static String regionList[]={"North America","Caribbean","South America","Asia","East Asia","Oceania","Middle East",
                                        "Africa","Western Europe","Eastern Europe"};
    /*
    Services
     */
    @Autowired
    userService userService;
    @Autowired
    filmService filmService;
    @Autowired
    songService songService;

    @GetMapping("/users") // users main page
    public String usersMain(Model model){
        List<User>userList=new ArrayList<>( userService.userList());
        //userList=userList.subList(1,userList.size());
        model.addAttribute("users",userList);
        return "users";
    }
    @GetMapping("/register") // create new user with a form
    public String resgisterButton(Model model){
        List<String> regList= Arrays.asList(regionList);
        model.addAttribute("regionList",regList);
        return "register";
    }

    @PostMapping("/register/newuser") // once you complete the form the new user is saved
    public String newUser(Model model, User user){
        userService.createUser(user);

        model.addAttribute("username",user.getUsername());
        return "newuser";
    }

    @GetMapping("/users/{id}") // full user information (attributes, song list and film list)
    public String userPage(Model model, @PathVariable long id){
        User user=userService.getUserById(id);
        model.addAttribute("id",user.getId());
        model.addAttribute("user",user);
        List<Song> songList=userService.getSongList(user.getId());
        List<Film> filmList=userService.getFilmList(id);
        model.addAttribute("songList",songList);
        model.addAttribute("filmList",filmList);
        return "userPage";
    }

    @GetMapping("/users/delete/{id}") // confirmation of user deletion, it is removed from the map
    public String deleteUser(Model model, @PathVariable long id){
        User user = userService.removeUser(id);
        model.addAttribute("name",user.getUsername());
        // when a user is deleted, the films and songs of the deleted user are property of user 0 (admin)
        filmService.deleteUser(id);
        songService.deleteUser(id);
        return "deleted";
    }

    @GetMapping("/updateUser/{id}") // update page with a form with the user information
    public String updateUserPage(Model model, @PathVariable long id){
        User user = userService.getUserById(id); // get the user by id (URL)
        model.addAttribute("user",user); // model to complete the form fields
        List<String> regList= Arrays.asList(regionList);
        model.addAttribute("regionList",regList);
        return "updateUser";
    }

    @PostMapping("/userInfo/{id}") // once the user complete the form it is redirected to the page containing its information
    public String updateUser(Model model,User user,@PathVariable long id){
        user.setId(id);
        model.addAttribute("user", user);
        userService.putUser(user,id); // the new user (info taken from the form) is put in the map
        return "redirect:/users/{id}";
    }
}
