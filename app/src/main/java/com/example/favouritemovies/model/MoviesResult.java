package com.example.favouritemovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesResult {

    @SerializedName("movies")
    @Expose
    private Movies movies;

    /**
     * @return The movies
     */
    public Movies getMovies() {
        return movies;
    }

    /**
     * @param movies The movies
     */
    public void setMovies(Movies movies) {
        this.movies = movies;
    }
}