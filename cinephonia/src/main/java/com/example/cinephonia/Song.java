package com.example.cinephonia;

public class Song {
    String name;
    String year;
    String minutes;
    String seconds;
    String author;

    public Song(String name, String year, String minutes, String seconds, String author) {
        this.name = name;
        this.year = year;
        this.minutes = minutes;
        this.seconds = seconds;
        this.author = author;
    }

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
}
