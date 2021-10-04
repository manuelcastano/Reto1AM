package com.example.reto1;

import android.net.Uri;

public class Profile {

    private String title;
    private Uri uri;
    private String description;


    public Profile(){

    }
    public Profile(String title, String description,Uri uri) {
        this.title = title;
        this.description = description;
        this.uri = uri;
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
    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
