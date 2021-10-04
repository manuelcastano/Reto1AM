package com.example.reto1;

import android.widget.ImageView;

public class Profile {

    private String title;
    //private ImageView image;
    private String description;


    public Profile(){

    }
    public Profile(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
