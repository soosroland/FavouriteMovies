package com.example.favouritemovies.interactor.events;

import com.example.favouritemovies.model.MovieDto;

import java.util.List;

public class GetMoviesEvent extends BaseEvent{
    List<MovieDto> movieDtoList;

    public GetMoviesEvent() {
    }

    public GetMoviesEvent(List<MovieDto> MovieDtoList) {
        this.movieDtoList = MovieDtoList;
    }

    public GetMoviesEvent(int code, Throwable throwable, List<MovieDto> movieDtoList) {
        super(code, throwable);
        this.movieDtoList = movieDtoList;
    }

    public List<MovieDto> getMovieDtoList() {
        return movieDtoList;
    }

    public void setMovieDtoList(List<MovieDto> movieDtoList) {
        this.movieDtoList = movieDtoList;
    }
}
