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

    /**
     * add a movie to the table
     */
    public void addMovie() {
        // add movie
    }


    /**
     * @return a movie by Id
     */
    public Movies getMovieById(int Id) {
        return null;
    }

    /**
     * update a movie
     */
    public void updateMovie(Movies movie){
        // update movie
    }


    /**
     * delete a movie
     */
    public void deleteMovie(int Id){
        // delete movie
    }

}