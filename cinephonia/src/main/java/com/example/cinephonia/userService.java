package com.example.cinephonia;

import org.springframework.stereotype.Service;

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

}
