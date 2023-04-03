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
    filmService filmService;

    interface FilmDetail extends Film.Basic, Film.Songs, Song.Basic{}

    @JsonView(FilmDetail.class)
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

    @JsonView(Film.Basic.class)
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
    public ResponseEntity<Film> deleteFilm(@PathVariable long id){
        Film film=filmService.removeFilm(id);
        if(film!=null){
            for(Song song:film.getSongs()){
                List<Film> newfilms = new ArrayList<>();
                for(Film thisFilm: song.getFilms()){
                    if(thisFilm.getId()!=id){
                        newfilms.add(thisFilm);
                    }
                }
                song.setFilms(newfilms);
            }
            return new ResponseEntity<>(film, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Film> putFilm(@PathVariable long id, @RequestBody Film fm){
        Film film = filmService.getFilmById(id);
        if(film!=null){
            filmService.putFilm(fm,id);
            for(Song song:film.getSongs()){
                List<Film> newfilms = new ArrayList<>();
                for(Film thisFilm: song.getFilms()){
                    if(thisFilm.getId()!=id){
                        newfilms.add(thisFilm);
                    }
                    else{
                        newfilms.add(fm);
                    }
                }
                song.setFilms(newfilms);
            }
            return new ResponseEntity<>(fm,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
