package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class filmService {
    private Map<Long, Film> films = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();

    public filmService() throws IOException { // initial films
        Film film = new Film("Love Actually","2003", "Richard Curtis",
                "This ultimate romantic comedy weaves together a spectacular number " +
                        "of love affairs into one amazing story. Set almost entirely in London, " +
                        "England during five frantic weeks before Christmas follows a web-like " +
                        "pattern of inter-related, losely related and unrelated stories of a dozen " +
                        "or more various individuals with their love lives, or lack of them."
                ,"Romance");
        long id = lastid.incrementAndGet();
        film.setId(id);
        film.createCover("loveactually.jpg","Collage");
        films.put(id,film);
        film = new Film("Interstellar", "2014", "Christopher Nolan",
                "In the near future Earth has been devastated by drought and " +
                        "famine, causing a scarcity in food and extreme changes in climate. " +
                        "When humanity is facing extinction, a mysterious rip in the space-time " +
                        "continuum is discovered, giving mankind the opportunity to widen their " +
                        "lifespan. A group of explorers must travel beyond our solar system in " +
                        "search of a planet that can sustain life.", "Science fiction");
        id = lastid.incrementAndGet();
        film.setId(id);
        film.createCover("interstellar.jpg","Landscape");
        films.put(id,film);
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
        id = lastid.incrementAndGet();
        film.setId(id);
        film.createCover("littlemermaid.jpg","Photograph");
        films.put(id,film);

        Files.createDirectories(Paths.get("C:/Cinephonia/covers"));
        Files.copy(Paths.get("src//main//resources//static//images/interstellar.jpg"),
                Paths.get("C:/Cinephonia/covers/interstellar.jpg"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src//main//resources//static//images/loveactually.jpg"),
                Paths.get("C:/Cinephonia/covers/loveactually.jpg"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src//main//resources//static//images/littlemermaid.jpg"),
                Paths.get("C:/Cinephonia/covers/littlemermaid.jpg"), StandardCopyOption.REPLACE_EXISTING);
    }

    // create Film method
    public void createFilm(Film film){
        long id=lastid.incrementAndGet(); // increment id
        film.setId(id);
        films.put(id,film); // add film to the map
    }

    public Collection<Film> filmList(){
        return films.values();
    }

    public Film removeFilm(long id){

        return films.remove(id);
    }

    public void putFilm(Film film, long id){ // change an existing film
        film.setId(id);
        films.put(id,film);
    }

    public Film getFilmById(long id){
        return films.get(id);
    } // returns film knowing its id

    public void deleteUser(long userId){
        // when a user is deleted, its films are admin's (user 0) property
        for(Film f: films.values()){
            if(f.getUserId()==userId){
                f.setUserId(0);
            }
        }
    }

    public void addSong(long id, Song song){ // add one song to the songs list of a film knowing its id
        List<Song> songs = getFilmById(id).getSongs();
        songs.add(song);
        getFilmById(id).setSongs(songs);
    }
}

