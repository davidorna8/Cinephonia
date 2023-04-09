package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class songService {
    private Map<Long, Song> songs = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();

    public songService(){ // initial songs
        Song song = new Song("The Trouble With Love Is", "2003",
                "3","41","Kelly Clarkson", "Pop");
        long id = lastid.incrementAndGet();
        song.setId(id);
        songs.put(id,song);
        song = new Song("Cornfield Chase","2014","2", "6",
                "Hans Zimmer","Original Soundtrack");
        id = lastid.incrementAndGet();
        song.setId(id);
        songs.put(id,song);
    }

    // create Song method
    public void createSong(Song song){
        long id=lastid.incrementAndGet();
        song.setId(id);
        songs.put(id,song);
    }

    public Collection<Song> songList(){
        return songs.values();
    }

    public Song removeSong(long id){
        return songs.remove(id);
    }

    public void putSong(Song song, long id){ // change an existing song
        song.setId(id);
        songs.put(id,song);
    }

    public Song getSongById(long id){
        return songs.get(id);
    } // returns a song knowing its id

    public void deleteUser(long userId){
        // when a user is deleted, its songs will be admin's (user 0) property
        for(Song s: songs.values()){
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

