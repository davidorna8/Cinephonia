package com.example.cinephonia;

public class Film {
    String name;
    String year;
    String director;
    String synopsis;

    public Film(String name, String year, String director, String synopsis) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
    }

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
}
