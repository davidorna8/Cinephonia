package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RequestMapping("/api/")
@RestController
public class songRESTController {
    @Autowired
    songService songService;

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
            return new ResponseEntity<>(sn,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
