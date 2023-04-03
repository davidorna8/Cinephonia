package com.example.cinephonia;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cover {
    @JsonView(Film.Basic.class)
    private String imageURL;
    @JsonView(Film.Basic.class)
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
