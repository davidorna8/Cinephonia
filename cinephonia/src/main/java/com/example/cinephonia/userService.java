package com.example.cinephonia;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class userService {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private AtomicLong lastid = new AtomicLong();
    private Map<String, Long> usernames = new ConcurrentHashMap<>();
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

}
