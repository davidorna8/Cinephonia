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
    @Autowired
    userService userService;

    @GetMapping("/users")
    public String usersMain(Model model){
        List<User>userList=new ArrayList<>( userService.userList());
        //userList=userList.subList(1,userList.size());
        model.addAttribute("users",userList);
        return "users";
    }
    @GetMapping("/register")
    public String resgisterButton(Model model){
        List<String> regList= Arrays.asList(regionList);
        model.addAttribute("regionList",regList);
        return "register";
    }

    @PostMapping("/register/newuser")
    public String newUser(Model model, User user){
        userService.createUser(user);

        model.addAttribute("username",user.getUsername());
        return "newuser";
    }

    @GetMapping("/users/{id}")
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

    @GetMapping("/users/delete/{id}")
    public String deleteUser(Model model, @PathVariable long id){
        User user = userService.removeUser(id);
        model.addAttribute("name",user.getUsername());
        //userService.removeFilm()
        return "deleted";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserPage(Model model, @PathVariable long id){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        List<String> regList= Arrays.asList(regionList);
        model.addAttribute("regionList",regList);
        return "updateUser";
    }

    @PostMapping("/userInfo/{id}")
    public String updateUser(Model model,User user,@PathVariable long id){
        user.setId(id);
        model.addAttribute("user", user);
        userService.putUser(user,id);
        return "userPage";
    }
}
