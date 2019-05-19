package com.example.favouritemovies.network;

import com.example.favouritemovies.model.MovieDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MovieApi {
    /**
     * Lists movies
     * Returns list of movies
     * @return Call<Movie>
     */

    @GET("movie")
    Call<List<MovieDto>> getMovies(
            @Header("movieId") String movieId
    );



    /**
     * Add a new movie to the list
     *
     * @param body Movie object that needs to be added to the list
     * @return Call<Void>
     */

    @POST("movie")
    Call<Void> addMovie(
            @Body MovieDto body, @Header("movieId") String movieId
    );


    /**
     * Find movie by ID
     * Returns a single movie
     * @param movieId ID of movie to return
     * @return Call<Movie>
     */
/*
    @GET("movie/{movieId}")
    Call<MovieDto> getMovieById(
            @Path("movieId") Long movieId
    );
*/

    /**
     * Update an existing movie
     *
     * @param movieId Movie id to update
     * @param body Movie object that needs to be added to the list
     * @return Call<Void>
     */

    @PUT("movie")
    Call<Void> updateMovie(
        @Body MovieDto body, @Header("movieId") String movieId
    );


    /**
     * Deletes a movie
     *
     * @param movieId Movie id to delete
     * @param deviceId
     * @return Call<Void>
     */

    @DELETE("movie/{movieId}")
    Call<Void> deleteMovie(
            @Header("deviceId") String deviceId, @Path("movieId") Long movieId
    );
}
