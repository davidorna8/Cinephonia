package com.example.cinephonia;

public class Cover {
    private String imageURL;
    private String style;

    public Cover(String imageURL, String style) {
        this.imageURL = imageURL;
        this.style=style;
    }

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
}
