package com.example.cinephonia.Controllers;

import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.Song;
import com.example.cinephonia.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@Controller
public class userController {
    private static String regionList[]={"North America","Caribbean","South America","Asia","East Asia","Oceania","Middle East",
                                        "Africa","Western Europe","Eastern Europe"};
    /*
    Services
     */
    @Autowired
    com.example.cinephonia.Services.userService userService;
    @Autowired
    com.example.cinephonia.Services.filmService filmService;
    @Autowired
    com.example.cinephonia.Services.songService songService;
    @Autowired
    com.example.cinephonia.Repositories.userRepository userRepository;
    @Autowired
    com.example.cinephonia.Repositories.filmRepository filmRepository;
    @Autowired
    com.example.cinephonia.Repositories.songRepository songRepository;

    @PostConstruct
    public void init(){
        User user= new User("Admin", "", "admin", "", "admin", "admin@admin.com", "");
        user.setId(0);
        userRepository.save(user);
        user=new User("David","Orna","david345","20","urjclol23","de.orna.2020@alumnos.urjc.es","Western Europe");
        userRepository.save(user);
        user=new User("Eva","Gomez","eva.g","20","%Ri8#kKl92","e.gomezf.2020@alumnos.urjc.es","Western Europe");
        userRepository.save(user);
        user=new User("John","Doe","yondou","56","JJnewof7","j.doe.fresh@hotmail.com","Asia");
        userRepository.save(user);
    }

    @GetMapping("/users") // users main page
    public String usersMain(Model model){
        List<User>userList=userRepository.findAll();
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
        userRepository.save(user);

        model.addAttribute("username",user.getUsername());
        return "newuser";
    }

    @GetMapping("/users/{id}") // full user information (attributes, song list and film list)
    public String userPage(Model model, @PathVariable long id){
        User user=userRepository.findById(id).get();
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
        User user = userRepository.findById(id).get();
        model.addAttribute("name",user.getUsername());
        // when a user is deleted, the films and songs of the deleted user are property of user 0 (admin)
        userRepository.delete(user);
        filmService.deleteUser(id);
        songService.deleteUser(id);
        return "deleted";
    }

    @GetMapping("/updateUser/{id}") // update page with a form with the user information
    public String updateUserPage(Model model, @PathVariable long id){
        User user = userRepository.findById(id).get(); // get the user by id (URL)
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
