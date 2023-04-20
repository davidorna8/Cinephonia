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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class filmService {
    private Map<Long, Film> films = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();
    @Autowired
    coverService coverService;

    public filmService() throws IOException { // initial film covers
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

