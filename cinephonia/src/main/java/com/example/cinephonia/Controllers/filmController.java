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

    ///CAMBIAR ESTOOOOOOOOOOOOOOOOOOOOOOO
    @Autowired
    com.example.cinephonia.Repositories.songRepository songRepository;

    @PostConstruct
    public void init(){ // initial lists for N:M relationship
        User admin= userService.createUser(new User("Admin", "", "admin", "", "admin", "admin@admin.com", ""));
        User david= userService.createUser(new User("David","Orna","david345","20","urjclol23","de.orna.2020@alumnos.urjc.es","Western Europe"));
        User eva= userService.createUser(new User("Eva","Gomez","eva.g","20","%Ri8#kKl92","e.gomezf.2020@alumnos.urjc.es","Western Europe"));
        User john= userService.createUser(new User("John","Doe","yondou","56","JJnewof7","j.doe.fresh@hotmail.com","Asia"));

        Song troubleLove = songService.createSong(new Song("The Trouble With Love Is", "2003",
                "3","41","Kelly Clarkson", "Pop"));
        troubleLove.setSongUser(admin);
        songService.createSong(troubleLove);
        Song cornfield = songService.createSong(new Song("Cornfield Chase","2014","2", "6",
                "Hans Zimmer","Original Soundtrack"));
        cornfield.setSongUser(admin);
        songService.createSong(cornfield);
        Song allAlong = songService.createSong(new Song("All Along the Watchtower","1968","4", "1",
                "Jimi Hendrix","Rock"));
        allAlong.setSongUser(john);
        songService.createSong(allAlong);
        Song stayin = songService.createSong(new Song("Stayin' Alive","1977","4", "9",
                "Bee Gees","Rock"));
        stayin.setSongUser(eva);
        songService.createSong(stayin);
        Song mrsRobinson= songService.createSong(new Song("Mrs. Robinson","1967","3", "55",
                "Simon and Garfunkel","Original Soundtrack"));
        mrsRobinson.setSongUser(david);
        songService.createSong(mrsRobinson);
        Song california = songService.createSong(new Song("California Somnolienta","1965","3", "2",
                "The Mamas and The Papas","Soul"));
        california.setSongUser(john);
        songService.createSong(california);
        Film loveActually = filmService.createFilm(new Film("Love Actually","2003", "Richard Curtis",
                "This ultimate romantic comedy weaves together a spectacular number " +
                        "of love affairs into one amazing story. Set almost entirely in London, " +
                        "England during five frantic weeks before Christmas follows a web-like " +
                        "pattern of inter-related, losely related and unrelated stories of a dozen " +
                        "or more various individuals with their love lives, or lack of them."
                ,"Romance"));
        Cover loveCover=coverService.createCover("loveactually.jpg","Collage");
        loveActually.setCover(loveCover);
        loveActually.setUser(userService.getUserByUsername("admin"));
        filmService.createFilm(loveActually);

        //loveActually.addSong(troubleLove);
        //troubleLove.addFilm(loveActually);

        Film interstellar = filmService.createFilm(new Film("Interstellar", "2014", "Christopher Nolan",
                "In the near future Earth has been devastated by drought and " +
                        "famine, causing a scarcity in food and extreme changes in climate. " +
                        "When humanity is facing extinction, a mysterious rip in the space-time " +
                        "continuum is discovered, giving mankind the opportunity to widen their " +
                        "lifespan. A group of explorers must travel beyond our solar system in " +
                        "search of a planet that can sustain life.", "Science fiction"));
        Cover interCover=coverService.createCover("interstellar.jpg","Landscape");
        interstellar.setCover(interCover);
        interstellar.setUser(userService.getUserByUsername("admin"));
        filmService.createFilm(interstellar);
        //interstellar.addSong(cornfield);
        //cornfield.addFilm(interstellar);
        Film littleMermaid= filmService.createFilm(new Film("The little Mermaid","2023","Rob Marshall",
                "The mermaid Ariel, daughter of King Triton, is fascinated with " +
                        "humans. She falls in love with the human prince Eric after she " +
                        "rescues him from a shipwreck. Condemned by her father for engaging " +
                        "in illicit contact with the surface world, Ariel then receives an offer " +
                        "from the scheming sea witch Ursula - Ursula will turn her into a human for " +
                        "three days, but during this time she must win the kiss of true love from Eric " +
                        "otherwise Ursula will own her forever. Ariel agrees but to add to the " +
                        "difficulty Ursula also takes Ariel's voice as price of the deal and then " +
                        "schemes to ensure that Ariel fails.", "Fantasy"));
        Cover littleCover=coverService.createCover("littlemermaid.jpg","Photograph");
        littleMermaid.setCover(littleCover);
        littleMermaid.setUser(userService.getUserByUsername("admin"));
        filmService.createFilm(littleMermaid);

        Film forrest= filmService.createFilm(new Film("Forrest Gump","1994","Robert Zemeckis",
                "Despite Forrest's (Tom Hanks) low IQ, he is not your average guy. " +
                        "Learning early on from his mother (Sally Field) that 'life is like a box of chocolates, you never know what you're gonna get'," +
                        " Gump, without trying, stumbles upon some exciting events. " +
                        "Meanwhile, as the remarkable parade of his life goes by, Forrest never forgets Jenny (Robin Wright), " +
                        "the girl he loved as a boy, who makes her own journey through the turbulence of the 1960s and 1970s " +
                        "that is far more troubled than the path Forrest happens upon."
                , "Drama"));
        Cover forrestCover=coverService.createCover("forrestgump.jpg","Photograph");
        forrest.setCover(forrestCover);
        forrest.setUser(userService.getUserByUsername("admin"));
        /*forrest.addSong(allAlong);
        allAlong.addFilm(forrest);
        forrest.addSong(mrsRobinson);
        mrsRobinson.addFilm(forrest);
        forrest.addSong(california);
        california.addFilm(forrest);*/
        filmService.createFilm(forrest);

        Film theGraduate = filmService.createFilm(new Film("The Graduate","1967","Mike Nichols",
                "In the mid-1960s, Benjamin Braddock (Dustin Hoffman), a confused college graduate, is pulled in myriad " +
                        "directions by his wealthy family, friends, and associates just days after receiving his degree." +
                        " Seduced by alcoholic and a neurotic Mrs. Robinson (Anne Bancroft), an older friend of the" +
                        " family and the wife of his father's law partner, Ben carries on an affair with the married" +
                        " woman even as he falls for her daughter, Elaine (Katharine Ross).","Romance"));
        Cover graduateCover =coverService.createCover("thegraduate.jpg","Photograph");
        theGraduate.setCover(graduateCover);
        theGraduate.setUser(david);
        /*theGraduate.addSong(mrsRobinson);
        mrsRobinson.addFilm(theGraduate);
        theGraduate.addSong(allAlong);
        allAlong.addFilm(theGraduate);*/
        filmService.createFilm(theGraduate);

        Film madagascar= filmService.createFilm(new Film("Madagascar","2005","Eric Darnell",
                "At New York's Central Park Zoo, a lion, a zebra, a giraffe, and a hippo are best friends and stars of " +
                        "the show. But when one of the animals goes missing from their cage, the other three break free " +
                        "to look for him, only to find themselves reunited ... on a ship en route to Africa. When their" +
                        " vessel is hijacked, however, the friends, who have all been raised in captivity, learn " +
                        "first-hand what life can be like in the wild.","Comedy"));

        madagascar.setUser(eva);
        Cover madagascarCover=coverService.createCover("madagascar.jpg","Animation");
        madagascar.setCover(madagascarCover);
        filmService.createFilm(madagascar);
        /*madagascar.addSong(stayin);
        stayin.addFilm(madagascar);*/

    }
    @GetMapping("/films") // Films main page
    public String filmsSection(Model model){

        // Model different lists for selects in the form
        List<String> genresList= Arrays.asList(genreList);
        model.addAttribute("genreList",genresList);
        List<String> styleList= Arrays.asList(stylesList);
        model.addAttribute("styleList",styleList);
        List<User> usersList = new ArrayList<>(userService.userList());
        //usersList= usersList.subList(1,usersList.size());
        model.addAttribute("users",usersList);

        // Model the full film list
        List<Film> filmList= new ArrayList<>(filmService.filmList());
        model.addAttribute("films",filmList);

        return "films";
    }

    @PostMapping("/filmInfo") // once the user has uploaded a new film, it is redirected to this page
    public String newFilm(Model model, Film film, @RequestParam("imageURL") MultipartFile imageURL,
                          @RequestParam String style, @RequestParam String username) throws IOException {

        // it takes the username of the user that uploaded the film in order to take its id
        User user= userService.getUserByUsername(username);
        film.setUser(user);

        // image saving:
        if(!imageURL.isEmpty()) {
            String absolutePath = "C://Cinephonia//covers";
            try {
                Path completePath = Paths.get(absolutePath + "//" + imageURL.getOriginalFilename());
                Files.write(completePath, imageURL.getBytes());
                Cover cover=coverService.createCover(imageURL.getOriginalFilename(), style);
                film.setCover(cover);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        filmService.createFilm(film); // creates the film with form data
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
        //filmService.deleteFilmFromSongs(film);
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
        film.setCover(oldFilm.getCover());
        film.setSongs(oldFilm.getSongs());
        film.setUser(oldFilm.getUser());
        String username= userService.getUserById(film.getUserId()).getUsername();

        // Model the film in order to show its information
        model.addAttribute("username",username);
        model.addAttribute("film",film);
        filmService.putFilm(film,id); // the new film (info taken from the form) is put in the map
        film.setId(id);
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
        filmService.putFilm(film,id);
        model.addAttribute("film", film);
        return "redirect:/films/{id}";
    }
}
