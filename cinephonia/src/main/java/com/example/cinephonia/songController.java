package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class songController {
    private static String genreList[]={"Punk","Rock","Pop","Classic", "Indie", "Blues", "Rap", "Original Soundtrack"};
    @Autowired
    songService songService;
    @Autowired
    userService userService;
    @GetMapping("/songs")
    public String songsSection(Model model){
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<Song> songList=new ArrayList<>(songService.songList());
        model.addAttribute("songs",songList);
        List<User> usersList = new ArrayList<>(userService.userList());
        usersList= usersList.subList(1,usersList.size());
        model.addAttribute("users",usersList);
        return "songs";
    }

    @PostMapping("/songInfo")
    public String newSong(Model model, Song song, @RequestParam String username){
        songService.createSong(song);
        long userId=userService.getUserByUsername(username).getId();
        song.setUserId(userId);
        model.addAttribute("username",username);
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
        model.addAttribute("id",song.getId());
        model.addAttribute("song",song);
        User u = userService.getUserById(song.getUserId());
        model.addAttribute("username",u.getUsername());
        return "songPage";
    }

    @GetMapping("/updateSong/{id}")
    public String updateSongPage(Model model, @PathVariable long id){
        Song song = songService.getSongById(id);
        model.addAttribute("song",song);
        String username= userService.getUserById(song.getUserId()).getUsername();
        model.addAttribute("username",username);
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        return "updateSong";
    }

    @PostMapping("/songInfo/{id}")
    public String updateSong(Model model,Song song,@PathVariable long id){
        song.setId(id);
        String username= userService.getUserById(song.getUserId()).getUsername();
        model.addAttribute("username",username);
        model.addAttribute("song", song);
        songService.putSong(song,id);
        return "songPage";
    }

    @GetMapping("/songs/delete/{id}")
    public String deleteSong(Model model,@PathVariable long id){
        Song song = songService.removeSong(id);
        model.addAttribute("name",song.getName());
        return "deleted";
    }
}
