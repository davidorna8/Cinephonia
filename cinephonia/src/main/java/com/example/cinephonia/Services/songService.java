package com.example.cinephonia.Services;

import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class songService {
    //private Map<Long, Song> songs = new ConcurrentHashMap<>();
    //private AtomicLong lastid = new AtomicLong();

    @Autowired
    com.example.cinephonia.Repositories.songRepository songRepository;

    /*public songService(){
        Song song = new Song("The Trouble With Love Is", "2003",
                "3","41","Kelly Clarkson", "Pop");
        createSong(song);
        Song troubleLove = song.copy();

        song = new Song("Cornfield Chase","2014","2", "6",
                "Hans Zimmer","Original Soundtrack");
        createSong(song);
        Song cornfield = song.copy();

        song = new Song("All Along the Watchtower","1968","4", "1",
                "Jimi Hendrix","Rock");
        createSong(song);
        Song allAlong = song.copy();

        song = new Song("Stayin' Alive","1977","4", "9",
                "Bee Gees","Rock");
        //song.setSongUser(eva);
        createSong(song);
        Song stayin = song.copy();

        song = new Song("Mrs. Robinson","1967","3", "55",
                "Simon and Garfunkel","Original Soundtrack");
        //song.setSongUser(david);
        createSong(song);
        Song mrsRobinson = song.copy();

        song = new Song("California Somnolienta","1965","3", "2",
                "The Mamas and The Papas","Soul");
        //song.setSongUser(john);
        createSong(song);
        Song california = song.copy();
    }*/
    // create Song method
    public Song createSong(Song song){
        Song s =songRepository.save(song);
        return s;
    }

    public Collection<Song> songList(){
        return songRepository.findAll();
    }

    public Song removeSong(long id){
        Song song=songRepository.findById(id).get();
        songRepository.delete(song);
        return song;
    }

    public void putSong(Song song, long id){ // change an existing song
        songRepository.save(song);
        song.setId(id);
    }

    public Song getSongById(long id){
        return songRepository.findById(id).get();
    } // returns a song knowing its id

    public Optional<Song> getOptional(long id){
        return songRepository.findById(id);
    }

    public void deleteUser(long userId){
        // when a user is deleted, its songs will be admin's (user 0) property
        for(Song s: songRepository.findAll()){
            if(s.getUserId()==userId){

            }
        }
    }

    public void addFilm(long id, Film film){ // add a film to the films list of song
        List<Film> films = getSongById(id).getFilms();
        films.add(film);
        getSongById(id).setFilms(films);
    }

    public void deleteSongFromFilms(Song song){
        long id = song.getId();
        // when you delete a song, it has to be deleted from all the songs list of the films containing that song
        for(Film film: song.getFilms()){
            // create a new list that doesn't contain the deleted song
            List<Song> newsongs = new ArrayList<>();
            for(Song thisSong: film.getSongs()){
                if(thisSong.getId()!=id){
                    newsongs.add(thisSong);
                }
            }
            film.setSongs(newsongs);
        }
    }

    public void updateSongFromFilms(Song song, Song oldSong, long id){
        // if song information is changed, films containing this song must update their songs list too
        for(Film film: oldSong.getFilms()){
            // create a new list in which the old song is deleted and the new song is added
            List<Song> newsongs = new ArrayList<>();
            for(Song thisSong: film.getSongs()){
                if(thisSong.getId()!=id){
                    newsongs.add(thisSong);
                }
                else{
                    newsongs.add(song);
                }
            }
            film.setSongs(newsongs);
        }
    }
}

