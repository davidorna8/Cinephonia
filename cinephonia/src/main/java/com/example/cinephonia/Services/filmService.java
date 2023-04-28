package com.example.cinephonia.Services;

import com.example.cinephonia.Models.Cover;
import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.Song;
import com.example.cinephonia.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    @Autowired
    com.example.cinephonia.Repositories.songRepository songRepository;
    @Autowired
    private EntityManager entityManager;

    // create Film method
    public Film createFilm(Film film){
        Film f=filmRepository.save(film);
        return f;
    }

    public Collection<Film> filmList(){
        return filmRepository.findAll();
    }

    public Film removeFilm(long id){
        Film film = filmRepository.findById(id).get();
        filmRepository.delete(film);
        return film;
    }

    public void putFilm(Film newFilm, Film film){ // change an existing film
        film.setDirector(newFilm.getDirector());
        film.setYear(newFilm.getYear());
        film.setSynopsis(newFilm.getSynopsis());
        film.setName(newFilm.getName());
        film.setGenre(newFilm.getGenre());
        filmRepository.save(film);

    }

    public Film getFilmById(long id){
        return filmRepository.findById(id).get();
    } // returns film knowing its id

    public Optional<Film> getOptional(long id){
        return filmRepository.findById(id);
    }
    public void deleteUser(long userId, User admin){
        // when a user is deleted, its films are admin's (user 0) property
        for(Film f: filmRepository.findAll()){
            if(f.getUserId()==userId){
                f.setUser(admin);
                filmRepository.save(f);
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

    public List<Film> filterFilm(String genre,String year){
        TypedQuery<Film> query = entityManager.createQuery
                ("SELECT f FROM Film f WHERE f.genre = :genre AND f.year >= :year", Film.class);
        List<Film>film=query.setParameter("genre",genre).setParameter("year",year).getResultList();
        return film;
    }
}

