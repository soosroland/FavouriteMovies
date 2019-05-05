package com.example.favouritemovies.network;

import com.example.favouritemovies.model.Movies;
import com.example.favouritemovies.model.MoviesResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {
    /**
     * Lists movies
     * Returns list of movies
     * @return Call<Movie>
     */

    @GET("movie")
    Call<Movies> getMovies();



    /**
     * Add a new movie to the list
     *
     * @param body Movie object that needs to be added to the list
     * @return Call<Void>
     */

    @POST("movie")
    Call<Void> addMovie(
            @Body Movies body
    );


    /**
     * Find movie by ID
     * Returns a single movie
     * @param movieId ID of movie to return
     * @return Call<Movie>
     */

    @GET("movie/{movieId}")
    Call<Movies> getMovieById(
            @Path("movieId") Long movieId
    );


    /**
     * Update an existing movie
     *
     * @param movieId Movie id to update
     * @param body Movie object that needs to be added to the list
     * @return Call<Void>
     */

    @PUT("movie/{movieId}")
    Call<Void> updateMovie(
            @Path("movieId") Long movieId, @Body Movies body
    );


    /**
     * Deletes a movie
     *
     * @param movieId Movie id to delete
     * @param apiKey
     * @return Call<Void>
     */

    @DELETE("movie/{movieId}")
    Call<Void> deleteMovie(
            @Path("movieId") Long movieId, @Header("api_key") String apiKey
    );
}
