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

    interface SongDetail extends Film.Basic, Song.Basic, Song.Films, Song.Users{}

    @JsonView(SongDetail.class)
    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSong(@PathVariable long id){
        Optional<Song> optionalSong = songService.getOptional(id);
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
        return songService.songList();
    }

    @JsonView(Song.Basic.class)
    @PostMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    public Song newSong(@RequestBody Song song){
        songService.createSong(song);
        return song;
    }

    @JsonView(Song.Basic.class)
    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable long id){
        Optional<Song> optionalSong=songService.getOptional(id);
        if(optionalSong.isPresent()){
            Song song=optionalSong.get();
            songService.deleteSongFromFilms(song);
            songService.removeSong(id);
            return new ResponseEntity<>(song, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @JsonView(Song.Basic.class)
    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> putSong(@PathVariable long id, @RequestBody Song sn){
        Optional<Song> optionalSong = songService.getOptional(id);
        if(optionalSong.isPresent()){
            Song song=optionalSong.get();
            songService.putSong(sn,song);
            songService.updateSongFromFilms(sn,song,id);
            return new ResponseEntity<>(sn,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
