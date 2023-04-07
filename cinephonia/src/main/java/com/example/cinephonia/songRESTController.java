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
public class songRESTController {
    @Autowired
    songService songService;

    interface SongDetail extends Film.Basic, Song.Basic, Song.Films{}

    @JsonView(SongDetail.class)
    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSong(@PathVariable long id){
        Song song = songService.getSongById(id);
        if(song!=null){
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

    @PostMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    public Song newSong(@RequestBody Song song){
        songService.createSong(song);
        return song;
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable long id){
        Song song=songService.removeSong(id);
        if(song!=null){
            songService.deleteSongFromFilms(song,id);
            return new ResponseEntity<>(song, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


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
