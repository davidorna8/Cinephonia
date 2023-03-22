package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class filmController {
    private static String genreList[]={"Action","Comedy","Western","Romance","Horror",
            "Science fiction","Thriller","Fantasy","Musical"};

    @Autowired
    filmService filmService;
    @GetMapping("/films")
    public String filmsSection(Model model){
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<Film> filmList=new ArrayList<>(filmService.filmList());
        model.addAttribute("films",filmList);
        return "films";
    }

    @PostMapping("/films")
    public String newFilm(Model model, Film film){
        filmService.createFilm(film);
        model.addAttribute("name",film.getName());
        return "films";
    }
}
