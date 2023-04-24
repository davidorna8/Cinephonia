package com.example.cinephonia.Services;

import com.example.cinephonia.Models.Cover;
import com.example.cinephonia.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class coverService {
    @Autowired
    com.example.cinephonia.Repositories.coverRepository coverRepository;

    public Cover createCover(String url, String style){
        Cover cover= new Cover(url,style);
        coverRepository.save(cover);
        return cover;
    }

    public Cover getCoverById(long id){
        return coverRepository.findById(id).get();
    }
}
