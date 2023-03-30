package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class filmService {
    private Map<Long, Film> films = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();

    public filmService(){
        Film film = new Film("Love Actually","2003", "Richard Curtis",
                "This ultimate romantic comedy weaves together a spectacular number " +
                        "of love affairs into one amazing story. Set almost entirely in London, " +
                        "England during five frantic weeks before Christmas follows a web-like " +
                        "pattern of inter-related, losely related and unrelated stories of a dozen " +
                        "or more various individuals with their love lives, or lack of them."
                ,"Romance");
        long id = lastid.incrementAndGet();
        film.setId(id);
        film.createCover("","");
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
        film.createCover("interstellar.jpg","");
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
        film.createCover("","");
        films.put(id,film);
    }
    // create Film method
    public void createFilm(Film film){
        long id=lastid.incrementAndGet();
        film.setId(id);
        films.put(id,film);
    }

    public Collection<Film> filmList(){
        return films.values();
    }

    public Film removeFilm(long id){

        return films.remove(id);
    }

    public void putFilm(Film film, long id){
        film.setId(id);
        films.put(id,film);
    }

    public Film getFilmById(long id){
        return films.get(id);
    }

    public void deleteUser(long userId){
        for(Film f: films.values()){
            if(f.getUserId()==userId){
                f.setUserId(0);
            }
        }
    }
}
