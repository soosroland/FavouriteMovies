package com.example.favouritemovies.persistence.repository;

import com.example.favouritemovies.model.MovieDto;
import com.example.favouritemovies.persistence.dao.MovieDao;

import java.util.List;

import javax.inject.Inject;

public class MovieRepository implements IMovieRepository{
    private MovieDao movieDao;

    @Inject
    public MovieRepository(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    public void insert(MovieDto... movies) {
        movieDao.insert(movies);
    }

    public void update(MovieDto... movies) {
        movieDao.update(movies);
    }

    public void delete(MovieDto movie) {
        movieDao.delete(movie);
    }

    public List<MovieDto> getAll(){
        return movieDao.getAll();
    }
}
