package com.example.cinephonia.Controllers;

import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.Song;
import com.example.cinephonia.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class songController { // Controller for pages containing songs
    private static String genreList[]={"Punk","Rock","Pop","Classic", "Indie", "Blues", "Rap", "Original Soundtrack","Soul"};
    /*
    Services
     */
    @Autowired
    com.example.cinephonia.Services.songService songService;
    @Autowired
    com.example.cinephonia.Services.userService userService;
    @Autowired
    com.example.cinephonia.Services.filmService filmService;
    @Autowired
    com.example.cinephonia.Repositories.songRepository songRepository;
    @Autowired
    com.example.cinephonia.Repositories.filmRepository filmRepository;
    @Autowired
    com.example.cinephonia.Repositories.userRepository userRepository;

    @PostConstruct
    public void init(){
        Song song = new Song("The Trouble With Love Is", "2003",
                "3","41","Kelly Clarkson", "Pop");
        songRepository.save(song);

        song = new Song("Cornfield Chase","2014","2", "6",
                "Hans Zimmer","Original Soundtrack");
        songRepository.save(song);

        song = new Song("All Along the Watchtower","1968","4", "1",
                "Jimi Hendrix","Rock");
        songRepository.save(song);

        song = new Song("Stayin' Alive","1977","4", "9",
                "Bee Gees","Rock");
        song.setUserId(2);
        songRepository.save(song);

        song = new Song("Mrs. Robinson","1967","3", "55",
                "Simon and Garfunkel","Original Soundtrack");
        song.setUserId(1);
        songRepository.save(song);

        song = new Song("California Somnolienta","1965","3", "2",
                "The Mamas and The Papas","Soul");
        song.setUserId(3);
        songRepository.save(song);
    }
    @GetMapping("/songs") // songs main page
    public String songsSection(Model model){

        // Model different lists for selects in the form
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<User> usersList = userRepository.findAll();
        usersList= usersList.subList(1,usersList.size());
        model.addAttribute("users",usersList);
        // Model full songs list
        List<Song> songList=songRepository.findAll();
        model.addAttribute("songs",songList);
        return "songs";
    }

    @PostMapping("/songInfo") // once the user has uploaded a new song, it is redirected to this page
    public String newSong(Model model, Song song, @RequestParam String username){
        songRepository.save(song); // creates the song with form data
        // it takes the username of the user that uploaded the film in order to take its id
        long userId=userService.getUserByUsername(username).getId();
        song.setUserId(userId);
        // Model for the page (username and all film information)
        model.addAttribute("username",username);
        List<Film> filmList= new ArrayList<>();
        for(Film film: filmRepository.findAll()){
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
        Song song=songRepository.findById(id).get();
        model.addAttribute("id",song.getId());
        model.addAttribute("song",song);

        // user that uploaded the song
        User u = userRepository.findById(song.getUserId()).get();
        model.addAttribute("username",u.getUsername());

        // list containing all uploaded films except for the ones in the song
        List<Film> filmList= new ArrayList<>();
        for(Film film: filmRepository.findAll()){
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
        Song song = songRepository.findById(id).get();
        model.addAttribute("name",song.getName());
        songService.deleteSongFromFilms(song);
        songRepository.delete(song);
        return "deleted";
    }

    @GetMapping("/updateSong/{id}") // page with a form where the user can change some attributes values
    public String updateSongPage(Model model, @PathVariable long id){
        // get the song by the id (URL) and model it to show its information in the form fields
        Song song = songRepository.findById(id).get();
        model.addAttribute("song",song);
        String username= userRepository.findById(song.getUserId()).get().getUsername();
        model.addAttribute("username",username);
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        return "updateSong";
    }

    @PostMapping("/songInfo/{id}") // when the user submits the update form a page containing the new film is shown
    public String updateSong(Model model,Song song,@PathVariable long id){
        Song oldSong = songRepository.findById(id).get();// the old song values are needed in order to mantain the id
        song.setFilms(oldSong.getFilms());
        song.setUserId(oldSong.getUserId());


        // Model the song in order to show its information
        String username= userRepository.findById(song.getUserId()).get().getUsername();
        List<Film> filmList= filmRepository.findAll();
        model.addAttribute("filmList",filmList);
        model.addAttribute("username",username);
        model.addAttribute("song", song);
        songRepository.save(song);// the new song (info taken from the form) is put in the map
        song.setId(id);
        songService.updateSongFromFilms(song,oldSong,id);
        return "redirect:/song/{id}";
    }


    @PostMapping("/song/{id}") // in the song page when the user adds films to the song
    public String addFilms(Model model,@RequestParam List<Long> selectedFilms, @PathVariable long id){
        // song id is taken from the URL, films list from the form of the html file
        Song song = songRepository.findById(id).get();
        songService.deleteSongFromFilms(song); // delete from the previous film list where the song appeared
        ArrayList<Film> films = new ArrayList<>();
        for(long filmId : selectedFilms){ // the form returns a list with ids of selected films
            Film film = filmRepository.findById(filmId).get();
            films.add(film); //add films to the films list of the song
            filmService.addSong(filmId,song); // add the song to the songs list of each film
        }
        song.setFilms(films);
        model.addAttribute("song", song);
        return "redirect:/song/{id}";
    }
}
