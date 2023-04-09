package com.example.cinephonia;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("/api/")
@RestController
public class filmRESTController {
    @Autowired
    filmService filmService; // Service

    // Interface for JsonView
    interface FilmDetail extends Film.Basic, Film.Songs, Song.Basic{}

    @JsonView(FilmDetail.class)
    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable long id){
        Film film = filmService.getFilmById(id); // get the film
        if(film!=null){
            return new ResponseEntity<>(film, HttpStatus.OK); // if it is found, return OK
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // if not found, response NOT FOUND
        }
    }

    @JsonView(Film.Basic.class)
    @GetMapping("/films")
    public Collection<Film> showFilms(){
        return filmService.filmList();
    } // full film list

    // create film with body (raw) of the API REST client (Postman)
    @JsonView(Film.Basic.class)
    @PostMapping("/films")
    @ResponseStatus(HttpStatus.CREATED)
    public Film newFilm(@RequestBody Film film){
        filmService.createFilm(film);
        // image not introduced, we save a copy of the default one with the introduced filename
        String imageURL = film.getCover().getImageURL();
        if (imageURL != null && !imageURL.isEmpty()) {
            String absolutePath = "C://Cinephonia//covers";
            try {
                Path completePath = Paths.get(absolutePath + "//" + imageURL);
                Files.copy(Paths.get("src//main//resources//static//images/default.jpg"),
                        completePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return film;
    }

    // add film cover image with body (form-data) including image file
    @JsonView(Film.Basic.class)
    @PostMapping("/films/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Film addImage(@RequestParam MultipartFile imageURL, @RequestParam String style,
                         @PathVariable long id) {
        Film film = filmService.getFilmById(id);
        // image saving:
        if(!imageURL.isEmpty()) {
            String absolutePath = "C://Cinephonia//covers";
            try {
                Path completePath = Paths.get(absolutePath + "//" + imageURL.getOriginalFilename());
                Files.write(completePath, imageURL.getBytes());
                film.createCover(imageURL.getOriginalFilename(), style);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return film;
    }

    @JsonView(Film.Basic.class)
    @DeleteMapping("/films/{id}")
    public ResponseEntity<Film> deleteFilm(@PathVariable long id){
        Film film=filmService.removeFilm(id); // gets the film by id
        if(film!=null){ // if it is found the film is deleted, from the list and the films list of each song
            filmService.deleteFilmFromSongs(film);
            return new ResponseEntity<>(film, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // return NOT FOUND
        }
    }

    @JsonView(Film.Basic.class)
    @PutMapping("/films/{id}")
    public ResponseEntity<Film> putFilm(@PathVariable long id, @RequestBody Film fm){
        Film film = filmService.getFilmById(id);
        if(film!=null){ // if the film exists
            filmService.putFilm(fm,id); // update film in the map
            filmService.updateFilmFromSongs(fm,film,id);
            return new ResponseEntity<>(fm,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
