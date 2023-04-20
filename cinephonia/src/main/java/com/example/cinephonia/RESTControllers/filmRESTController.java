package com.example.cinephonia.RESTControllers;

import com.example.cinephonia.Models.Cover;
import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.Song;
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
import java.util.Collection;
import java.util.Optional;

@RequestMapping("/api/")
@RestController
public class filmRESTController {
    @Autowired
    com.example.cinephonia.Services.filmService filmService; // Service
    @Autowired
    com.example.cinephonia.Services.coverService coverService;
    @Autowired
    com.example.cinephonia.Repositories.filmRepository filmRepository;

    // Interface for JsonView
    interface FilmDetail extends Film.Basic, Film.Songs, Song.Basic {}

    @JsonView(FilmDetail.class)
    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable long id){
        Optional<Film> optionalFilm = filmRepository.findById(id); // get the film
        if(optionalFilm.isPresent()){
            Film film = optionalFilm.get();
            return new ResponseEntity<>(film, HttpStatus.OK); // if it is found, return OK
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // if not found, response NOT FOUND
        }
    }

    @JsonView(Film.Basic.class)
    @GetMapping("/films")
    public Collection<Film> showFilms(){
        return filmRepository.findAll();
    } // full film list

    // create film with body (raw) of the API REST client (Postman)
    @JsonView(Film.Basic.class)
    @PostMapping("/films")
    @ResponseStatus(HttpStatus.CREATED)
    public Film newFilm(@RequestBody Film film){
        filmRepository.save(film);
        // image not introduced, we save a copy of the default one with the introduced filename
        String imageURL = coverService.getCoverById(film.getCoverId()).getImageURL();
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
        Film film = filmRepository.findById(id).get();
        // image saving:
        if(!imageURL.isEmpty()) {
            String absolutePath = "C://Cinephonia//covers";
            try {
                Path completePath = Paths.get(absolutePath + "//" + imageURL.getOriginalFilename());
                Files.write(completePath, imageURL.getBytes());
                Cover cover=coverService.createCover(imageURL.getOriginalFilename(), style);
                film.setCoverId(cover.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return film;
    }

    @JsonView(Film.Basic.class)
    @DeleteMapping("/films/{id}")
    public ResponseEntity<Film> deleteFilm(@PathVariable long id){
        Optional<Film> optionalFilm = filmRepository.findById(id); // gets the film by id
        if(optionalFilm.isPresent()){ // if it is found the film is deleted, from the list and the films list of each song
            Film film = optionalFilm.get();
            filmService.deleteFilmFromSongs(film);
            filmRepository.delete(film);
            return new ResponseEntity<>(film, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // return NOT FOUND
        }
    }

    @JsonView(Film.Basic.class)
    @PutMapping("/films/{id}")
    public ResponseEntity<Film> putFilm(@PathVariable long id, @RequestBody Film fm){
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if(optionalFilm.isPresent()){ // if the film exists
            filmRepository.save(fm);
            fm.setId(id);
            Film film = optionalFilm.get();
            //filmService.putFilm(fm,id); // update film in the map
            filmService.updateFilmFromSongs(fm,film,id);
            return new ResponseEntity<>(fm,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
