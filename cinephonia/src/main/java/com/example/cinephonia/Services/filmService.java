package com.example.cinephonia.Services;

import com.example.cinephonia.Models.Cover;
import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class filmService {
    @Autowired
    coverService coverService;
    @Autowired
    com.example.cinephonia.Repositories.filmRepository filmRepository;


    /*public filmService() throws IOException { // initial film covers
        Film film = new Film("Love Actually","2003", "Richard Curtis",
                "This ultimate romantic comedy weaves together a spectacular number " +
                        "of love affairs into one amazing story. Set almost entirely in London, " +
                        "England during five frantic weeks before Christmas follows a web-like " +
                        "pattern of inter-related, losely related and unrelated stories of a dozen " +
                        "or more various individuals with their love lives, or lack of them."
                ,"Romance");
        Cover cover=coverService.createCover("loveactually.jpg","Collage");
        film.setCover(cover);
        createFilm(film);
        Film loveActually= getFilmById(film.getId());
        //loveActually.addSong(troubleLove);
        //troubleLove.addFilm(loveActually);


        film = new Film("Interstellar", "2014", "Christopher Nolan",
                "In the near future Earth has been devastated by drought and " +
                        "famine, causing a scarcity in food and extreme changes in climate. " +
                        "When humanity is facing extinction, a mysterious rip in the space-time " +
                        "continuum is discovered, giving mankind the opportunity to widen their " +
                        "lifespan. A group of explorers must travel beyond our solar system in " +
                        "search of a planet that can sustain life.", "Science fiction");
        cover=coverService.createCover("interstellar.jpg","Landscape");
        film.setCover(cover);
        createFilm(film);
        Film interstellar =getFilmById(film.getId());
        //interstellar.addSong(cornfield);
        //cornfield.addFilm(interstellar);


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
        film.setCover(cover);
        createFilm(film);

        film= new Film("Forrest Gump","1994","Robert Zemeckis",
                "Despite Forrest's (Tom Hanks) low IQ, he is not your average guy. " +
                        "Learning early on from his mother (Sally Field) that 'life is like a box of chocolates, you never know what you're gonna get'," +
                        " Gump, without trying, stumbles upon some exciting events. " +
                        "Meanwhile, as the remarkable parade of his life goes by, Forrest never forgets Jenny (Robin Wright), " +
                        "the girl he loved as a boy, who makes her own journey through the turbulence of the 1960s and 1970s " +
                        "that is far more troubled than the path Forrest happens upon."
                , "Drama");
        cover=coverService.createCover("forrestgump.jpg","Photograph");
        film.setCover(cover);
        //film.setUser(david);
        createFilm(film);
        Film forrest = getFilmById(film.getId());
        //forrest.addSong(allAlong);
        //allAlong.addFilm(forrest);
        //forrest.addSong(mrsRobinson);
        //mrsRobinson.addFilm(forrest);
        //forrest.addSong(california);
        //california.addFilm(forrest);


        film= new Film("The Graduate","1967","Mike Nichols",
                "In the mid-1960s, Benjamin Braddock (Dustin Hoffman), a confused college graduate, is pulled in myriad " +
                        "directions by his wealthy family, friends, and associates just days after receiving his degree." +
                        " Seduced by alcoholic and a neurotic Mrs. Robinson (Anne Bancroft), an older friend of the" +
                        " family and the wife of his father's law partner, Ben carries on an affair with the married" +
                        " woman even as he falls for her daughter, Elaine (Katharine Ross).","Romance");
        cover=coverService.createCover("thegraduate.jpg","Photograph");
        film.setCover(cover);
        createFilm(film);
        Film theGraduate = getFilmById(film.getId());
        //theGraduate.addSong(mrsRobinson);
        //mrsRobinson.addFilm(theGraduate);
        //theGraduate.addSong(allAlong);
        //allAlong.addFilm(theGraduate);


        film= new Film("Madagascar","2005","Eric Darnell",
                "At New York's Central Park Zoo, a lion, a zebra, a giraffe, and a hippo are best friends and stars of " +
                        "the show. But when one of the animals goes missing from their cage, the other three break free " +
                        "to look for him, only to find themselves reunited ... on a ship en route to Africa. When their" +
                        " vessel is hijacked, however, the friends, who have all been raised in captivity, learn " +
                        "first-hand what life can be like in the wild.","Comedy");
        //film.setUser(eva);
        cover=coverService.createCover("madagascar.jpg","Animation");
        film.setCover(cover);
        createFilm(film);
        Film madagascar = getFilmById(film.getId());
        //madagascar.addSong(stayin);
        //stayin.addFilm(madagascar);
        Files.createDirectories(Paths.get("C:/Cinephonia/covers"));
        Files.copy(Paths.get("src//main//resources//static//images/interstellar.jpg"),
                Paths.get("C:/Cinephonia/covers/interstellar.jpg"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src//main//resources//static//images/loveactually.jpg"),
                Paths.get("C:/Cinephonia/covers/loveactually.jpg"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src//main//resources//static//images/littlemermaid.jpg"),
                Paths.get("C:/Cinephonia/covers/littlemermaid.jpg"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src//main//resources//static//images/forrestgump.jpg"),
                Paths.get("C:/Cinephonia/covers/forrestgump.jpg"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src//main//resources//static//images/thegraduate.jpg"),
                Paths.get("C:/Cinephonia/covers/thegraduate.jpg"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src//main//resources//static//images/madagascar.jpg"),
                Paths.get("C:/Cinephonia/covers/madagascar.jpg"), StandardCopyOption.REPLACE_EXISTING);
    }*/

    // create Film method
    public void createFilm(Film film){
        filmRepository.save(film);
    }

    public Collection<Film> filmList(){
        return filmRepository.findAll();
    }

    public Film removeFilm(long id){
        Film film = filmRepository.findById(id).get();
        filmRepository.delete(film);
        return film;
    }

    public void putFilm(Film film, long id){ // change an existing film
        filmRepository.save(film);
        film.setId(id);
    }

    public Film getFilmById(long id){
        return filmRepository.findById(id).get();
    } // returns film knowing its id

    public Optional<Film> getOptional(long id){
        return filmRepository.findById(id);
    }
    public void deleteUser(long userId){
        // when a user is deleted, its films are admin's (user 0) property
        for(Film f: filmRepository.findAll()){
            if(f.getUserId()==userId){
                //f.setUser(userService.);
            }
        }
    }

    public void addSong(long id, Song song){ // add one song to the songs list of a film knowing its id
        List<Song> songs = getFilmById(id).getSongs();
        songs.add(song);
        getFilmById(id).setSongs(songs);
    }

    public void deleteFilmFromSongs(Film film){
        long id = film.getId();
        // when you delete a film, it has to be deleted from all the films list of the songs containing that film
        for(Song song:film.getSongs()){
            // create a new list that doesn't contain the deleted film
            List<Film> newfilms = new ArrayList<>();
            for(Film thisFilm: song.getFilms()){
                if(thisFilm.getId()!=id){
                    newfilms.add(thisFilm);
                }
            }
            song.setFilms(newfilms);
        }
    }

    public void updateFilmFromSongs(Film film,Film oldFilm, long id){
        // if film information is changed, songs containing this film must update their film list too
        for(Song song:oldFilm.getSongs()){
            List<Film> newfilms = new ArrayList<>();
            // create a new list in which the old film is deleted and the new film is added
            for(Film thisFilm: song.getFilms()){
                if(thisFilm.getId()!=id){
                    newfilms.add(thisFilm);
                }
                else{
                    newfilms.add(film);
                }
            }
            song.setFilms(newfilms);
        }
    }

}

