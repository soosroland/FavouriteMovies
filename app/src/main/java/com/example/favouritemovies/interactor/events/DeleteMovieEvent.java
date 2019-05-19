package com.example.favouritemovies.interactor.events;

import com.example.favouritemovies.model.MovieDto;

public class DeleteMovieEvent extends BaseEvent {
    private MovieDto removedMovieDto;

    public DeleteMovieEvent(MovieDto removedMovieDto) {
        this.removedMovieDto = removedMovieDto;
    }

    public DeleteMovieEvent(int code, Throwable throwable, MovieDto removedMovieDto) {
        super(code, throwable);
        this.removedMovieDto = removedMovieDto;
    }

    public DeleteMovieEvent() {
    }

    public DeleteMovieEvent(int code, Throwable throwable) {
        super(code, throwable);
    }

    public MovieDto getRemovedMovieDto() {
        return removedMovieDto;
    }

    public void setRemovedMovieDto(MovieDto removedMovieDto) {
        this.removedMovieDto = removedMovieDto;
    }
}
