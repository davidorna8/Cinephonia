package com.example.cinephonia;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class songService {
    private Map<Long, Song> songs = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();

    public songService(){
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

    public void putSong(Song song, long id){
        song.setId(id);
        songs.put(id,song);
    }

    public Song getSongById(long id){
        return songs.get(id);
    }

}
