package com.example.cinephonia.Controllers;

import com.example.cinephonia.Models.Cover;
import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.Song;
import com.example.cinephonia.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class filmController { // Controller for different pages containing films
    private static String genreList[]={"Action","Comedy","Western","Romance","Horror",
            "Science fiction","Thriller","Fantasy","Musical","Drama"};

    private static String stylesList[]={"Collage","Animation","Photograph","Landscape"};

    /*
    Services used in the controller
     */
    @Autowired
    com.example.cinephonia.Services.filmService filmService;
    @Autowired
    com.example.cinephonia.Services.userService userService;
    @Autowired
    com.example.cinephonia.Services.songService songService;

    @Autowired
    com.example.cinephonia.Services.coverService coverService;
    @Autowired
    com.example.cinephonia.Repositories.filmRepository filmRepository;
    @Autowired
    com.example.cinephonia.Repositories.songRepository songRepository;

    @PostConstruct
    public void init(){ // initial lists for N:M relationship
        Film film = new Film("Love Actually","2003", "Richard Curtis",
                "This ultimate romantic comedy weaves together a spectacular number " +
                        "of love affairs into one amazing story. Set almost entirely in London, " +
                        "England during five frantic weeks before Christmas follows a web-like " +
                        "pattern of inter-related, losely related and unrelated stories of a dozen " +
                        "or more various individuals with their love lives, or lack of them."
                ,"Romance");
        Cover cover=coverService.createCover("loveactually.jpg","Collage");
        film.setCoverId(cover.getId());

        Film loveActually= filmRepository.findById(1L).get();
        Song troubleLove = songRepository.findById(1L).get();
        loveActually.addSong(troubleLove);
        troubleLove.addFilm(loveActually);
        filmRepository.save(film);

        Film interstellar = filmService.getFilmById(2);
        Song cornfield = songService.getSongById(2);
        interstellar.addSong(cornfield);
        cornfield.addFilm(interstellar);

        Film forrest = filmService.getFilmById(4);
        Song allAlong = songService.getSongById(3);
        forrest.addSong(allAlong);
        allAlong.addFilm(forrest);
        Song mrsRobinson = songService.getSongById(5);
        forrest.addSong(mrsRobinson);
        mrsRobinson.addFilm(forrest);
        Song california = songService.getSongById(6);
        forrest.addSong(california);
        california.addFilm(forrest);

        Film theGraduate = filmService.getFilmById(5);
        theGraduate.addSong(mrsRobinson);
        mrsRobinson.addFilm(theGraduate);
        theGraduate.addSong(allAlong);
        allAlong.addFilm(theGraduate);

        Film madagascar = filmService.getFilmById(6);
        Song stayin = songService.getSongById(4);
        madagascar.addSong(stayin);
        stayin.addFilm(madagascar);
    }
    @GetMapping("/films") // Films main page
    public String filmsSection(Model model){

        // Model different lists for selects in the form
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<String> styleList= Arrays.asList(stylesList);
        model.addAttribute("styleList",styleList);
        List<User> usersList = new ArrayList<>(userService.userList());
        usersList= usersList.subList(1,usersList.size());
        model.addAttribute("users",usersList);

        // Model the full film list
        List<Film> filmList=new ArrayList<>(filmService.filmList());
        model.addAttribute("films",filmList);

        return "films";
    }

    @PostMapping("/filmInfo") // once the user has uploaded a new film, it is redirected to this page
    public String newFilm(Model model, Film film, @RequestParam("imageURL") MultipartFile imageURL,
                          @RequestParam String style, @RequestParam String username) throws IOException {
        filmService.createFilm(film); // creates the film with form data
        // it takes the username of the user that uploaded the film in order to take its id
        long userId= userService.getUserByUsername(username).getId();
        film.setUserId(userId);

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
        // Model for the page (username and all film information)
        model.addAttribute("username",username);
        model.addAttribute("film",film);
        long id=film.getId();
        model.addAttribute("id",id);
        List<Song> songList = new ArrayList<>(songService.songList());
        model.addAttribute("songList", songList);
        return "filmPage";
    }

    @GetMapping("/films/{id}") // when the user clicks "See more" we show the information of the film
    public String filmPage(Model model, @PathVariable long id){
        // take the film by id and model it
        Film film=filmService.getFilmById(id);
        model.addAttribute("id",film.getId());
        model.addAttribute("film",film);

        // user that uploaded the film:
        User u = userService.getUserById(film.getUserId());
        model.addAttribute("username",u.getUsername());

        // songs that don't appear in the film
        List<Song> songList = new ArrayList<>();
        for(Song song: songService.songList()){
            if(!film.containsSong(song)){
                songList.add(song);
            }
        }
        model.addAttribute("songList", songList);
        return "filmPage";
    }

    @GetMapping("/films/delete/{id}") // page returned when you delete a film from the website
    public String deleteFilm(Model model, @PathVariable long id){
        // remove it from the map
        Film film= filmService.removeFilm(id);
        model.addAttribute("name",film.getName());
        filmService.deleteFilmFromSongs(film);
        return "deleted";
    }

    @GetMapping("/updateFilm/{id}") // page with a form where the user can change some attributes values
    public String updateFilmPage(Model model, @PathVariable long id){
        // get the film by the id in the URL and model it to show its information in the form fields
        Film film = filmService.getFilmById(id);
        model.addAttribute("film",film);
        String username= userService.getUserById(film.getUserId()).getUsername();
        model.addAttribute("username",username);
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        return "updateFilm";
    }

    @PostMapping("/filmInfo/{id}")
    // once the user updates film information, it is redirected to a page showing the new information
    public String updateFilm(Model model,Film film,@PathVariable long id){
        Film oldFilm = filmService.getFilmById(id); // the old film values are needed to mantain the cover and id
        film.setCoverId(oldFilm.getCoverId());
        film.setId(id);
        film.setSongs(oldFilm.getSongs());
        film.setUserId(oldFilm.getUserId());
        String username= userService.getUserById(film.getUserId()).getUsername();

        // Model the film in order to show its information
        model.addAttribute("username",username);
        model.addAttribute("film",film);
        filmService.putFilm(film,id); // the new film (info taken from the form) is put in the map
        List<Song> songList = new ArrayList<>(songService.songList());
        model.addAttribute("songList", songList);
        filmService.updateFilmFromSongs(film,oldFilm,id);
        return "redirect:/films/{id}";
    }

    @PostMapping("/films/{id}") // in the film page when the user adds songs to the film
    public String addSongs(Model model,@RequestParam List<Long> selectedSongs, @PathVariable long id){
        // film id is taken from the URL, songs list from the form of the html file
        Film film = filmService.getFilmById(id);
        filmService.deleteFilmFromSongs(film); // delete from the previous song list where the film appeared
        ArrayList<Song> songs = new ArrayList<>();
        for(long songId : selectedSongs){ // the form returns a list with ids of selected songs
            Song song = songService.getSongById(songId);
            songs.add(song); //add songs to the songs list of the film
            songService.addFilm(songId,film); // add the film to the films list of each song
        }
        film.setSongs(songs);
        model.addAttribute("film", film);
        return "redirect:/films/{id}";
    }
}
