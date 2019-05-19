package com.example.favouritemovies.mock;

import com.example.favouritemovies.model.MovieDto;
import com.example.favouritemovies.persistence.dao.MovieDao;
import com.example.favouritemovies.persistence.repository.IMovieRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class MockMovieRepository implements IMovieRepository {

    private static Map<Long, MovieDto> moviesDb = new HashMap<>();
    private static long id = 1;

    private MovieDao movieDao;

    @Inject
    public MockMovieRepository(MovieDao movieDao) {
        this.movieDao = movieDao;
        MovieDto movieDto = new MovieDto();
        movieDto.setId(10L);
        movieDto.setTitle("Harry Potter 3");
        movieDto.setYear(2000);
        movieDto.setRating(8.1);
        moviesDb.put(id++, movieDto);
    }

    public void insert(MovieDto... movies) {
        for (MovieDto movie: movies) {
            moviesDb.put(id++, movie);
        }
    }

    public void update(MovieDto... movies) {
        for (MovieDto movie: movies) {
            moviesDb.put(id++, movie);
        }
    }

    public void delete(MovieDto movie) {
        moviesDb.remove(movie);
    }

    public List<MovieDto> getAll() { return new ArrayList<>(moviesDb.values()); }

    public void deleteAll() {
        moviesDb.clear();
    }
}
