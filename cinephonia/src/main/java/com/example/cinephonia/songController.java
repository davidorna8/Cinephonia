package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class songController {
    private static String genreList[]={"Punk","Rock","Pop","Classic", "Indie", "Blues", "Rap", "Original Soundtrack"};
    @Autowired
    songService songService;
    @Autowired
    userService userService;
    @Autowired
    filmService filmService;
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
        List<Film> filmList= new ArrayList<>(filmService.filmList());
        model.addAttribute("filmList",filmList);
        return "songPage";
    }

    @GetMapping("/song/{id}")
    public String songPage(Model model, @PathVariable long id){
        Song song=songService.getSongById(id);
        model.addAttribute("id",song.getId());
        model.addAttribute("song",song);
        User u = userService.getUserById(song.getUserId());
        model.addAttribute("username",u.getUsername());
        List<Film> filmList= new ArrayList<>(filmService.filmList());
        model.addAttribute("filmList",filmList);
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
        Song oldSong = songService.getSongById(id);
        song.setFilms(oldSong.getFilms());
        song.setId(id);
        String username= userService.getUserById(song.getUserId()).getUsername();
        List<Film> filmList= new ArrayList<>(filmService.filmList());
        model.addAttribute("filmList",filmList);
        model.addAttribute("username",username);
        model.addAttribute("song", song);
        songService.putSong(song,id);
        for(Film film: oldSong.getFilms()){
            List<Song> newsongs = new ArrayList<>();
            for(Song thisSong: film.getSongs()){
                if(thisSong.getId()!=id){
                    newsongs.add(thisSong);
                }
                else{
                    newsongs.add(song);
                }
            }
            film.setSongs(newsongs);
        }
        return "songPage";
    }

    @GetMapping("/songs/delete/{id}")
    public String deleteSong(Model model,@PathVariable long id){
        Song song = songService.removeSong(id);
        model.addAttribute("name",song.getName());
        for(Film film: song.getFilms()){
            List<Song> newsongs = new ArrayList<>();
            for(Song thisSong: film.getSongs()){
                if(thisSong.getId()!=id){
                    newsongs.add(thisSong);
                }
            }
            film.setSongs(newsongs);
        }
        return "deleted";
    }

    @PostMapping("/song/{id}")
    public String addFilms(Model model,@RequestParam List<Long> selectedFilms, @PathVariable long id){
        Song song = songService.getSongById(id);
        ArrayList<Film> films = new ArrayList<>();
        for(long filmId : selectedFilms){
            Film film = filmService.getFilmById(filmId);
            films.add(film);
            filmService.addSong(filmId,song);
        }
        song.setFilms(films);
        model.addAttribute("song", song);
        return "redirect:/song/{id}";
    }
}
