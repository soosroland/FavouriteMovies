package com.example.favouritemovies.mock;

import com.example.favouritemovies.model.MovieDto;
import com.example.favouritemovies.network.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockMovieApi implements MovieApi {
    private static MovieDto movie;

    @Override
    public Call<List<MovieDto>> getMovies(String deviceId) {
        final List<MovieDto> res = new ArrayList<>();


        MovieDto movieDto = new MovieDto();
        movieDto.setId(100L);
        movieDto.setTitle("Harry Potter 1");
        movieDto.setYear(1997);
        movieDto.setRating(7.5);
        res.add(movieDto);

        movieDto = new MovieDto();
        movieDto.setId(101L);
        movieDto.setTitle("Harry Potter 2");
        movieDto.setYear(1998);
        movieDto.setRating(7.3);
        res.add(movieDto);

        //Test after add/update/delete movie
        if(movie != null){
            res.add(movie);
        }

        return new Call<List<MovieDto>>() {
            @Override
            public Response<List<MovieDto>> execute() throws IOException {
                return Response.success(res);
            }

            @Override
            public void enqueue(Callback<List<MovieDto>> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<List<MovieDto>> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }

    @Override
    public Call<Void> updateMovie(MovieDto body, String deviceId) {
        movie = body;
        return new Call<Void>() {
            @Override
            public Response<Void> execute() throws IOException {
                return Response.success(200, null);
            }

            @Override
            public void enqueue(Callback<Void> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Void> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }

    @Override
    public Call<Void> addMovie(MovieDto body, String deviceId) {
        movie = body;
        return new Call<Void>() {
            @Override
            public Response<Void> execute() throws IOException {
                return Response.success(200, null);
            }

            @Override
            public void enqueue(Callback<Void> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Void> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }

    @Override
    public Call<Void> deleteMovie(String deviceId, Long movieId) {
        movie = null;
        return new Call<Void>() {
            @Override
            public Response<Void> execute() throws IOException {
                return Response.success(200, null);
            }

            @Override
            public void enqueue(Callback<Void> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Void> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }
}
