package com.example.favouritemovies.interactor.events;


import com.example.favouritemovies.model.MovieDto;

public class CreateMovieEvent extends BaseEvent {
    private MovieDto movieDto;

    public CreateMovieEvent() {
    }

    public CreateMovieEvent(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public CreateMovieEvent(int code, Throwable throwable, MovieDto movieDto) {
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
