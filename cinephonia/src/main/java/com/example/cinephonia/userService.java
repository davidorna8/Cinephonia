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
public class userService {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();
    @Autowired
    songService songService;
    @Autowired
    filmService filmService;

    public userService(){
        User user= new User("Admin", "", "admin", "", "admin", "admin@admin.com", "");
        user.setId(0);
        users.put((long)0,user);
        user=new User("David","Orna","david345","20","urjclol23","de.orna.2020@alumnos.urjc.es","Western Europe");
        long id=lastid.incrementAndGet();
        user.setId(id);
        users.put(id,user);
        user=new User("Eva","Gomez","eva.g","20","%Ri8#kKl92","e.gomezf.2020@alumnos.urjc.es","Western Europe");
        id=lastid.incrementAndGet();
        user.setId(id);
        users.put(id,user);
        user=new User("John","Doe","yondou","56","JJnewof7","j.doe.fresh@hotmail.com","Asia");
        id=lastid.incrementAndGet();
        user.setId(id);
        users.put(id,user);
    }

    // create User method
    public void createUser(User user){
        long id=lastid.incrementAndGet();
        user.setId(id);
        users.put(id,user);
    }

    public Collection<User> userList(){
        return users.values();
    }

    public User removeUser(long id){
        return users.remove(id);
    }

    public void putUser(User user, long id){
        user.setId(id);
        users.put(id,user);
    }

    public User getUserById(long id){
        return users.get(id);
    }

    public User getUserByUsername(String username){
        for(User u : users.values()){
            if(u.getUsername().equals(username)) return u;
        }
        return new User();
    }

    public List<Song> getSongList(long id){
        List<Song> allSongs=new ArrayList<>(songService.songList());
        List<Song> userSongList=new ArrayList<>();
        for(Song s: allSongs){
            if(s.getUserId()==id){
                userSongList.add(s);
            }
        }
        return userSongList;
    }

    public List<Film> getFilmList(long id){
        List<Film> allFilms=new ArrayList<>(filmService.filmList());
        List<Film> userFilmList=new ArrayList<>();
        for(Film s: allFilms){
            if(s.getUserId()==id){
                userFilmList.add(s);
            }
        }
        return userFilmList;
    }

}
