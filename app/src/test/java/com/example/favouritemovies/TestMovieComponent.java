package com.example.favouritemovies;

import com.example.favouritemovies.mock.MockNetworkModule;
import com.example.favouritemovies.mock.MockRoomModule;
import com.example.favouritemovies.persistence.repository.IMovieRepository;
import com.example.favouritemovies.test.movie.MovieTest;
import com.example.favouritemovies.test.movies.MoviesTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, MockRoomModule.class, TestAppModule.class})
public interface  TestMovieComponent extends MovieApplicationComponent{
    void inject(MovieTest movieTest);

    void inject(MoviesTest moviesTest);

    IMovieRepository MockMovieRepository();
}
