package com.example.favouritemovies.persistence.repository;

import com.example.favouritemovies.model.MovieDto;

import java.util.List;

public interface IMovieRepository {
    public void insert(MovieDto... movies);

    public void update(MovieDto... movies);

    public void delete(MovieDto movie);

    public List<MovieDto> getAll();
}
