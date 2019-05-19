package com.example.favouritemovies.mock;

import com.example.favouritemovies.persistence.MovieDatabase;
import com.example.favouritemovies.persistence.dao.MovieDao;
import com.example.favouritemovies.persistence.repository.IMovieRepository;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockRoomModule {
    @Singleton
    @Provides
    MovieDatabase providesMovieDatabase() {
        return Mockito.mock(MovieDatabase.class);
    }

    @Singleton
    @Provides
    MovieDao providesMovieDao(MovieDatabase movieDatabase) {
        return Mockito.mock(MovieDao.class);
    }

    @Singleton
    @Provides
    IMovieRepository movieRepository(MovieDao movieDao) {
        return new MockMovieRepository(movieDao);
    }
}
