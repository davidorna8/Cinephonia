package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class filmController {
    private static String genreList[]={"Action","Comedy","Western","Romance","Horror",
            "Science fiction","Thriller","Fantasy","Musical"};

    private static String stylesList[]={"Collage","Animation","Photograph","Landscape"};
    @Autowired
    filmService filmService;
    @Autowired
    userService userService;
    @GetMapping("/films")
    public String filmsSection(Model model){
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<String> styleList= Arrays.asList(stylesList);
        model.addAttribute("styleList",styleList);
        List<Film> filmList=new ArrayList<>(filmService.filmList());
        model.addAttribute("films",filmList);
        List<User> usersList = new ArrayList<>(userService.userList());
        usersList= usersList.subList(1,usersList.size());
        model.addAttribute("users",usersList);
        return "films";
    }

    @PostMapping("/filmInfo")
    public String newFilm(Model model, Film film, @RequestParam("imageURL") MultipartFile imageURL,
                          @RequestParam String style, @RequestParam String username) throws IOException {
        filmService.createFilm(film);
        long userId= userService.getUserByUsername(username).getId();
        film.setUserId(userId);

        if(!imageURL.isEmpty()) {
            Path directory = Paths.get("cinephonia//src//main//resources//static/images");
            String absolutePath = directory.toFile().getAbsolutePath();
            try {
                Path completePath = Paths.get(absolutePath + "//" + imageURL.getOriginalFilename());
                Files.write(completePath, imageURL.getBytes());
                film.createCover(imageURL.getOriginalFilename(), style);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    @GetMapping("/films/delete/{id}")
    public String deleteFilm(Model model, @PathVariable long id){
        Film film= filmService.removeFilm(id);
        model.addAttribute("name",film.getName());
        //userService.removeFilm()
        return "deleted";
    }

    @GetMapping("/updateFilm/{id}")
    public String updateFilmPage(Model model, @PathVariable long id){
        Film film = filmService.getFilmById(id);
        model.addAttribute("film",film);
        String username= userService.getUserById(film.getUserId()).getUsername();
        model.addAttribute("username",username);
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        return "updateFilm";
    }

    @PostMapping("/filmInfo/{id}")
    public String updateFilm(Model model,Film film,@PathVariable long id){
        Film oldFilm = filmService.getFilmById(id);
        film.setCover(oldFilm.getCover());
        film.setId(id);
        String username= userService.getUserById(film.getUserId()).getUsername();
        model.addAttribute("username",username);
        model.addAttribute("film",film);
        filmService.putFilm(film,id);
        return "filmPage";
    }
}
