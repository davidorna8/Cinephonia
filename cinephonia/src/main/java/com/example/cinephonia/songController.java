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
public class songController { // Controller for pages containing songs
    private static String genreList[]={"Punk","Rock","Pop","Classic", "Indie", "Blues", "Rap", "Original Soundtrack","Soul"};
    /*
    Services
     */
    @Autowired
    songService songService;
    @Autowired
    userService userService;
    @Autowired
    filmService filmService;
    @GetMapping("/songs") // songs main page
    public String songsSection(Model model){

        // Model different lists for selects in the form
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<User> usersList = new ArrayList<>(userService.userList());
        usersList= usersList.subList(1,usersList.size());
        model.addAttribute("users",usersList);
        // Model full songs list
        List<Song> songList=new ArrayList<>(songService.songList());
        model.addAttribute("songs",songList);
        return "songs";
    }

    @PostMapping("/songInfo") // once the user has uploaded a new song, it is redirected to this page
    public String newSong(Model model, Song song, @RequestParam String username){
        songService.createSong(song); // creates the song with form data
        // it takes the username of the user that uploaded the film in order to take its id
        long userId=userService.getUserByUsername(username).getId();
        song.setUserId(userId);
        // Model for the page (username and all film information)
        model.addAttribute("username",username);
        List<Film> filmList= new ArrayList<>();
        for(Film film: filmService.filmList()){
            if(!song.containsFilm(film)){
                filmList.add(film);
            }
        }
        model.addAttribute("filmList",filmList);
        return "songPage";
    }

    @GetMapping("/song/{id}") // when the user clicks "See more" we show the information of the song
    public String songPage(Model model, @PathVariable long id){
        // take the song by id and model it
        Song song=songService.getSongById(id);
        model.addAttribute("id",song.getId());
        model.addAttribute("song",song);

        // user that uploaded the song
        User u = userService.getUserById(song.getUserId());
        model.addAttribute("username",u.getUsername());

        // list containing all uploaded films except for the ones in the song
        List<Film> filmList= new ArrayList<>();
        for(Film film: filmService.filmList()){
            if(!song.containsFilm(film)){
                filmList.add(film);
            }
        }
        model.addAttribute("filmList",filmList);
        return "songPage";
    }

    @GetMapping("/songs/delete/{id}") // page returned when you delete a film from the website
    public String deleteSong(Model model,@PathVariable long id){
        // remove it from the map
        Song song = songService.removeSong(id);
        model.addAttribute("name",song.getName());
        songService.deleteSongFromFilms(song);
        return "deleted";
    }

    @GetMapping("/updateSong/{id}") // page with a form where the user can change some attributes values
    public String updateSongPage(Model model, @PathVariable long id){
        // get the song by the id (URL) and model it to show its information in the form fields
        Song song = songService.getSongById(id);
        model.addAttribute("song",song);
        String username= userService.getUserById(song.getUserId()).getUsername();
        model.addAttribute("username",username);
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        return "updateSong";
    }

    @PostMapping("/songInfo/{id}") // when the user submits the update form a page containing the new film is shown
    public String updateSong(Model model,Song song,@PathVariable long id){
        Song oldSong = songService.getSongById(id);// the old song values are needed in order to mantain the id
        song.setFilms(oldSong.getFilms());
        song.setId(id);

        // Model the song in order to show its information
        String username= userService.getUserById(song.getUserId()).getUsername();
        List<Film> filmList= new ArrayList<>(filmService.filmList());
        model.addAttribute("filmList",filmList);
        model.addAttribute("username",username);
        model.addAttribute("song", song);
        songService.putSong(song,id);// the new song (info taken from the form) is put in the map
        songService.updateSongFromFilms(song,oldSong,id);
        return "redirect:/song/{id}";
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
