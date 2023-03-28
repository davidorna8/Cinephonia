package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class filmController {
    private static String genreList[]={"Action","Comedy","Western","Romance","Horror",
            "Science fiction","Thriller","Fantasy","Musical"};

    @Autowired
    filmService filmService;

    @Autowired
    userService userService;
    @GetMapping("/films")
    public String filmsSection(Model model){
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<Film> filmList=new ArrayList<>(filmService.filmList());
        model.addAttribute("films",filmList);
        List<User> usersList = new ArrayList<>(userService.userList());
        usersList= usersList.subList(1,usersList.size());
        model.addAttribute("users",usersList);
        return "films";
    }

    @PostMapping("/filmInfo")
    public String newFilm(Model model, Film film, @RequestParam String username){
        filmService.createFilm(film);
        long userId= userService.getUserByUsername(username).getId();
        film.setUserId(userId);
        model.addAttribute("username",username);
        model.addAttribute("film",film);
        return "filmPage";
    }

    @GetMapping("/films/{id}")
    public String filmPage(Model model, @PathVariable long id){
        Film film=filmService.getFilmById(id);
        model.addAttribute("id",film.getId());
        model.addAttribute("film",film);
        User u = userService.getUserById(film.getUserId());
        model.addAttribute("username",u.getUsername());

        return "filmPage";
    }
}
