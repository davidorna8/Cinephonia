package com.example.cinephonia.Services;

import com.example.cinephonia.Models.Cover;
import com.example.cinephonia.Models.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class coverService {
    private Map<Long, Cover> covers = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();

    public Cover createCover(String url, String style){
        Cover cover= new Cover(url,style);
        long id= lastid.incrementAndGet();
        cover.setId(id);
        covers.put(id,cover);
        return cover;
    }

    public Cover getCoverById(long id){
        return covers.get(id);
    }
}
