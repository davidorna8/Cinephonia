package com.example.cinephonia.Services;

import com.example.cinephonia.Models.Cover;
import com.example.cinephonia.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class coverService {
    @Autowired
    com.example.cinephonia.Repositories.coverRepository coverRepository;

    public coverService() throws IOException {
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
    public Cover createCover(String url, String style){
        Cover cover= coverRepository.save(new Cover(url,style));
        return cover;
    }

    public Cover getCoverById(long id){
        return coverRepository.findById(id).get();
    }
}
