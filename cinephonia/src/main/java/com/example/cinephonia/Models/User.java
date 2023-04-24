package com.example.cinephonia.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User { // Java class for users
    /*
    Attributes
     */
    private String name;
    private String surname;
    private String username;
    private String age;
    private String password;
    private String email;
    private String region;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "songUser")
    private List<Song> songs;

    @OneToMany(mappedBy = "filmUser")
    private List<Song> films;
    /*
    Constructors
     */
    public User(String name, String surname, String username, String age, String password, String email, String region) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.age = age;
        this.password = password;
        this.email = email;
        this.region = region;
    }
    /*
    Getters and Setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
