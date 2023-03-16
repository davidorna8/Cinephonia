package com.example.cinephonia;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class filmService {
    private Map<Long, Film> films = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();

    // create Film method
    public void createFilm(Film film){
        long id=lastid.incrementAndGet();
        film.setId(id);
        films.put(id,film);
    }

}
