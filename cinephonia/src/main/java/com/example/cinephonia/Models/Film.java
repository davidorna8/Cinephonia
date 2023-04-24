package com.example.cinephonia.Models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Film { // Java class for Films
    /*
    Interfaces for JsonView
     */
    public interface Basic{}
    public interface Songs{}
    /*
    Attributes
     */
    @JsonView(Basic.class)
    private String name;
    @JsonView(Basic.class)
    private String year;
    @JsonView(Basic.class)
    private String director;
    @JsonView(Basic.class)
    private String synopsis;
    @JsonView(Basic.class)
    private String genre;
    @ManyToOne
    @JsonView(Basic.class)
    private User filmUser; // each film is uploaded by one user (1:N relationship) (user foreign key)
    @JsonView(Basic.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonView(Basic.class)
    private Cover cover; // each film has one cover (1:1 relationship)
    @JsonView(Songs.class)
    private List<Song> songs= new ArrayList<>(); // each film has many songs (N:M relationship)
    /*
    Constructor
     */
    public Film(String name, String year, String director, String synopsis,String genre) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
        this.genre=genre;
    }
    /*
    Getters and Setters
     */
    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public long getUserId() {
        return filmUser.getId();
    }

    public User getUser() {
        return filmUser;
    }

    public void setUser(User user) {
        filmUser=user;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    /* Methods for N:M relationship */
    public boolean containsSong(Song song){
        return songs.contains(song);
    }

    public void addSong(Song song){
        songs.add(song);
    }
}
