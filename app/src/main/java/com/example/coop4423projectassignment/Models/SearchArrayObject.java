package com.example.coop4423projectassignment.Models;

public class SearchArrayObject {
    String original_title = "";
    String poster_path = "";
    Integer id = null;
    float popularity;

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
