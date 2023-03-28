package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class songController {
    private static String genreList[]={"Punk","Rock","Pop","Classic", "Indie", "Blues", "Rap", "Original Soundtrack"};
    @Autowired
    songService songService;
    @GetMapping("/songs")
    public String songsSection(Model model){
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        return "songs";
    }

    @PostMapping("/songInfo")
    public String newSong(Model model, Song song){
        songService.createSong(song);
        //model.addAttribute("name",song.getName());
        return "songPage";
    }

    @GetMapping("/prueba")
    public String hhh(Model model){
        List<Song> list= new ArrayList<>(songService.songList());
        model.addAttribute("songs",list);
        return "pruebita";
    }

    @GetMapping("/song/{id}")
    public String songPage(Model model, @PathVariable long id){
        Song song=songService.getSongById(id);
        model.addAttribute("song",song);
        return "filmPage";
    }
}
