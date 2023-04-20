package com.example.cinephonia.RESTControllers;

import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.Song;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RequestMapping("/api/")
@RestController
public class songRESTController {
    @Autowired
    com.example.cinephonia.Services.songService songService;
    @Autowired
    com.example.cinephonia.Repositories.songRepository songRepository;

    interface SongDetail extends Film.Basic, Song.Basic, Song.Films{}

    @JsonView(SongDetail.class)
    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSong(@PathVariable long id){
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()){
            Song song=optionalSong.get();
            return new ResponseEntity<>(song, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @JsonView(Song.Basic.class)
    @GetMapping("/songs")
    public Collection<Song> showSongs(){
        return songRepository.findAll();
    }

    @JsonView(Song.Basic.class)
    @PostMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    public Song newSong(@RequestBody Song song){
        songRepository.save(song);
        return song;
    }

    @JsonView(Song.Basic.class)
    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable long id){
        Optional<Song> optionalSong=songRepository.findById(id);
        if(optionalSong.isPresent()){
            Song song=songRepository.findById(id).get();
            songService.deleteSongFromFilms(song);
            songRepository.delete(song);
            return new ResponseEntity<>(song, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @JsonView(Song.Basic.class)
    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> putSong(@PathVariable long id, @RequestBody Song sn){
        Song song = songService.getSongById(id);
        if(song!=null){
            songService.putSong(sn,id);
            songService.updateSongFromFilms(sn,song,id);
            return new ResponseEntity<>(sn,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
