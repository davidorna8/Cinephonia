package com.example.cinephonia.Models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song { // Java class for songs
    /*
    Interfaces for JsonView
     */
    public interface Basic {}
    public interface Films {}
    public interface Users {}
    /*
    Attributes
     */
    @JsonView(Basic.class)
    private String name;
    @JsonView(Basic.class)
    private String year;
    @JsonView(Basic.class)
    private String minutes;
    @JsonView(Basic.class)
    private String seconds;
    @JsonView(Basic.class)
    private String author;
    @JsonView(Basic.class)
    private String genre;
    @JoinColumn(name="\"user\"")
    @ManyToOne
    @JsonView(Users.class)
    private User songUser; // each song is uploaded by one user (1:N relationship) (user foreign key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Basic.class)
    private long id=1L;
    @ManyToMany
    @JsonView(Films.class)
    private List<Film> films= new ArrayList<>(); // each song has many films (N:M relationship)
    /*
    Constructor
     */
    public Song(String name, String year, String minutes, String seconds, String author, String genre) {
        this.name = name;
        this.year = year;
        this.minutes = minutes;
        this.seconds = seconds;
        this.author = author;
        this.genre= genre;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return songUser.getId();
    }

    public User getSongUser() {
        return songUser;
    }

    public void setSongUser(User songUser) {
        this.songUser = songUser;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    /* Methods for N:M relationship */
    public boolean containsFilm(Film film){
        return films.contains(film);
    }

    public void addFilm(Film film){
        films.add(film);
    }


}
