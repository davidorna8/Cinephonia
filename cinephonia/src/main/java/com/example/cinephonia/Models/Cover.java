package com.example.cinephonia.Models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Cover { // java class for film covers
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id=1L;

    @JsonView(Film.Basic.class)
    private String imageURL; // image path
    @JsonView(Film.Basic.class)
    private String style;

    /*
    Constructor
     */
    public Cover(String imageURL, String style) {
        this.imageURL = imageURL;
        this.style=style;
    }

    /*
    Getters and Setters
     */
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
