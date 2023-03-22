package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api/")
@RestController
public class filmRESTController {
    @Autowired
    filmService filmService;

    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable long id){
        Film film = filmService.getFilmById(id);
        if(film!=null){
            return new ResponseEntity<>(film, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/films")
    public Collection<Film> showFilms(){
        return filmService.filmList();
    }
    @PostMapping("/films")
    @ResponseStatus(HttpStatus.CREATED)
    public Film newFilm(@RequestBody Film film){
        filmService.createFilm(film);
        return film;
    }
    @DeleteMapping("/films/{id}")
    public ResponseEntity<Film> deleteSong(@PathVariable long id){
        Film film=filmService.removeFilm(id);
        if(film!=null){
            return new ResponseEntity<>(film, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Film> putSong(@PathVariable long id, @RequestBody Film fm){
        Film film = filmService.getFilmById(id);
        if(film!=null){
            filmService.putFilm(fm,id);
            return new ResponseEntity<>(fm,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
