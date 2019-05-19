package com.example.favouritemovies.ui.movies;


import com.example.favouritemovies.model.MovieDto;

import java.util.List;

public interface MoviesScreen {
    void showMovies(List<MovieDto> movieDtoList);

    void showNetworkError(String message);

    void showMessage(String message);

    void remove(MovieDto movieDto);
}
