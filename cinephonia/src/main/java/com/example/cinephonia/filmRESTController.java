package com.example.cinephonia;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    // create film with body of the API REST client (Postman)
    @JsonView(Film.Basic.class)
    @PostMapping("/films")
    @ResponseStatus(HttpStatus.CREATED)
    public Film newFilm(@RequestBody Film film){
        filmService.createFilm(film);
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
