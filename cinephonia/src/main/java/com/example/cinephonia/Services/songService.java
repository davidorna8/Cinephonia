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

    // create Song method
    public void createSong(Song song){
        songRepository.save(song);
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
                s.setUserId(0);
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

