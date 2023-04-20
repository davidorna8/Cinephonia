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
    @Autowired
    com.example.cinephonia.Repositories.userRepository userRepository;

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

        film = new Film("Interstellar", "2014", "Christopher Nolan",
                "In the near future Earth has been devastated by drought and " +
                        "famine, causing a scarcity in food and extreme changes in climate. " +
                        "When humanity is facing extinction, a mysterious rip in the space-time " +
                        "continuum is discovered, giving mankind the opportunity to widen their " +
                        "lifespan. A group of explorers must travel beyond our solar system in " +
                        "search of a planet that can sustain life.", "Science fiction");
        cover=coverService.createCover("interstellar.jpg","Landscape");
        film.setCoverId(cover.getId());

        Film interstellar = filmRepository.findById(2L).get();
        Song cornfield = songRepository.findById(2L).get();
        interstellar.addSong(cornfield);
        cornfield.addFilm(interstellar);
        filmRepository.save(film);

        film= new Film("The little Mermaid","2023","Rob Marshall",
                "The mermaid Ariel, daughter of King Triton, is fascinated with " +
                        "humans. She falls in love with the human prince Eric after she " +
                        "rescues him from a shipwreck. Condemned by her father for engaging " +
                        "in illicit contact with the surface world, Ariel then receives an offer " +
                        "from the scheming sea witch Ursula - Ursula will turn her into a human for " +
                        "three days, but during this time she must win the kiss of true love from Eric " +
                        "otherwise Ursula will own her forever. Ariel agrees but to add to the " +
                        "difficulty Ursula also takes Ariel's voice as price of the deal and then " +
                        "schemes to ensure that Ariel fails.", "Fantasy");
        cover=coverService.createCover("littlemermaid.jpg","Photograph");
        film.setCoverId(cover.getId());
        filmRepository.save(film);

        film= new Film("Forrest Gump","1994","Robert Zemeckis",
                "Despite Forrest's (Tom Hanks) low IQ, he is not your average guy. " +
                        "Learning early on from his mother (Sally Field) that 'life is like a box of chocolates, you never know what you're gonna get'," +
                        " Gump, without trying, stumbles upon some exciting events. " +
                        "Meanwhile, as the remarkable parade of his life goes by, Forrest never forgets Jenny (Robin Wright), " +
                        "the girl he loved as a boy, who makes her own journey through the turbulence of the 1960s and 1970s " +
                        "that is far more troubled than the path Forrest happens upon."
                , "Drama");
        cover=coverService.createCover("forrestgump.jpg","Photograph");
        film.setCoverId(cover.getId());
        film.setUserId(1);

        Film forrest = filmRepository.findById(4L).get();
        Song allAlong = songRepository.findById(3L).get();
        forrest.addSong(allAlong);
        allAlong.addFilm(forrest);
        Song mrsRobinson = songRepository.findById(5L).get();
        forrest.addSong(mrsRobinson);
        mrsRobinson.addFilm(forrest);
        Song california = songRepository.findById(6L).get();
        forrest.addSong(california);
        california.addFilm(forrest);
        filmRepository.save(film);

        film= new Film("The Graduate","1967","Mike Nichols",
                "In the mid-1960s, Benjamin Braddock (Dustin Hoffman), a confused college graduate, is pulled in myriad " +
                        "directions by his wealthy family, friends, and associates just days after receiving his degree." +
                        " Seduced by alcoholic and a neurotic Mrs. Robinson (Anne Bancroft), an older friend of the" +
                        " family and the wife of his father's law partner, Ben carries on an affair with the married" +
                        " woman even as he falls for her daughter, Elaine (Katharine Ross).","Romance");
        cover=coverService.createCover("thegraduate.jpg","Photograph");
        film.setCoverId(cover.getId());

        Film theGraduate = filmRepository.findById(5L).get();
        theGraduate.addSong(mrsRobinson);
        mrsRobinson.addFilm(theGraduate);
        theGraduate.addSong(allAlong);
        allAlong.addFilm(theGraduate);
        filmRepository.save(film);

        film= new Film("Madagascar","2005","Eric Darnell",
                "At New York's Central Park Zoo, a lion, a zebra, a giraffe, and a hippo are best friends and stars of " +
                        "the show. But when one of the animals goes missing from their cage, the other three break free " +
                        "to look for him, only to find themselves reunited ... on a ship en route to Africa. When their" +
                        " vessel is hijacked, however, the friends, who have all been raised in captivity, learn " +
                        "first-hand what life can be like in the wild.","Comedy");
        film.setUserId(2);
        cover=coverService.createCover("madagascar.jpg","Animation");
        film.setCoverId(cover.getId());

        Film madagascar = filmRepository.findById(6L).get();
        Song stayin = songRepository.findById(4L).get();
        madagascar.addSong(stayin);
        stayin.addFilm(madagascar);
        filmRepository.save(film);
    }
    @GetMapping("/films") // Films main page
    public String filmsSection(Model model){

        // Model different lists for selects in the form
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<String> styleList= Arrays.asList(stylesList);
        model.addAttribute("styleList",styleList);
        List<User> usersList = userRepository.findAll();
        usersList= usersList.subList(1,usersList.size());
        model.addAttribute("users",usersList);

        // Model the full film list
        List<Film> filmList= filmRepository.findAll();
        model.addAttribute("films",filmList);

        return "films";
    }

    @PostMapping("/filmInfo") // once the user has uploaded a new film, it is redirected to this page
    public String newFilm(Model model, Film film, @RequestParam("imageURL") MultipartFile imageURL,
                          @RequestParam String style, @RequestParam String username) throws IOException {
        filmRepository.save(film); // creates the film with form data
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
        List<Song> songList = songRepository.findAll();
        model.addAttribute("songList", songList);
        return "filmPage";
    }

    @GetMapping("/films/{id}") // when the user clicks "See more" we show the information of the film
    public String filmPage(Model model, @PathVariable long id){
        // take the film by id and model it
        Film film=filmRepository.findById(id).get();
        model.addAttribute("id",film.getId());
        model.addAttribute("film",film);

        // user that uploaded the film:
        User u = userRepository.findById(film.getUserId()).get();
        model.addAttribute("username",u.getUsername());

        // songs that don't appear in the film
        List<Song> songList = new ArrayList<>();
        for(Song song: songRepository.findAll()){
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
        Film film= filmRepository.findById(id).get();
        model.addAttribute("name",film.getName());
        filmService.deleteFilmFromSongs(film);
        filmRepository.delete(film);
        return "deleted";
    }

    @GetMapping("/updateFilm/{id}") // page with a form where the user can change some attributes values
    public String updateFilmPage(Model model, @PathVariable long id){
        // get the film by the id in the URL and model it to show its information in the form fields
        Film film = filmRepository.findById(id).get();
        model.addAttribute("film",film);
        String username= userRepository.findById(film.getUserId()).get().getUsername();
        model.addAttribute("username",username);
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        return "updateFilm";
    }

    @PostMapping("/filmInfo/{id}")
    // once the user updates film information, it is redirected to a page showing the new information
    public String updateFilm(Model model,Film film,@PathVariable long id){
        Film oldFilm = filmRepository.findById(id).get(); // the old film values are needed to mantain the cover and id
        film.setCoverId(oldFilm.getCoverId());
        film.setSongs(oldFilm.getSongs());
        film.setUserId(oldFilm.getUserId());
        String username= userRepository.findById(film.getUserId()).get().getUsername();

        // Model the film in order to show its information
        model.addAttribute("username",username);
        model.addAttribute("film",film);
        //filmService.putFilm(film,id); // the new film (info taken from the form) is put in the map
        filmRepository.save(film);
        film.setId(id);
        List<Song> songList = songRepository.findAll();
        model.addAttribute("songList", songList);
        filmService.updateFilmFromSongs(film,oldFilm,id);
        return "redirect:/films/{id}";
    }

    @PostMapping("/films/{id}") // in the film page when the user adds songs to the film
    public String addSongs(Model model,@RequestParam List<Long> selectedSongs, @PathVariable long id){
        // film id is taken from the URL, songs list from the form of the html file
        Film film = filmRepository.findById(id).get();
        filmService.deleteFilmFromSongs(film); // delete from the previous song list where the film appeared
        ArrayList<Song> songs = new ArrayList<>();
        for(long songId : selectedSongs){ // the form returns a list with ids of selected songs
            Song song = songRepository.findById(songId).get();
            songs.add(song); //add songs to the songs list of the film
            songService.addFilm(songId,film); // add the film to the films list of each song
        }
        film.setSongs(songs);
        model.addAttribute("film", film);
        return "redirect:/films/{id}";
    }
}
