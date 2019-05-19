package com.example.favouritemovies.interactor.events;

import com.example.favouritemovies.model.MovieDto;

public class UpdateMovieEvent extends BaseEvent {
    private MovieDto movieDto;

    public UpdateMovieEvent() {
    }

    public UpdateMovieEvent(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public UpdateMovieEvent(int code, Throwable throwable, MovieDto movieDto) {
        super(code, throwable);
        this.movieDto = movieDto;
    }

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }
}
