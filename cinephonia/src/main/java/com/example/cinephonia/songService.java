package com.example.cinephonia;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class songService {
    private Map<Long, Song> songs = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();

    // create Song method
    public void createSong(Song song){
        long id=lastid.incrementAndGet();
        song.setId(id);
        songs.put(id,song);
    }

}
