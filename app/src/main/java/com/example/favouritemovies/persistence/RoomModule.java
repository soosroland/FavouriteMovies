package com.example.favouritemovies.persistence;

import android.app.Application;

import com.example.favouritemovies.persistence.dao.MovieDao;
import com.example.favouritemovies.persistence.repository.IMovieRepository;
import com.example.favouritemovies.persistence.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private MovieDatabase movieDatabase;

    public RoomModule(Application mApplication) {
        movieDatabase = MovieDatabase.getDatabase(mApplication);
    }

    @Singleton
    @Provides
    MovieDatabase providesMovieDatabase() {
        return movieDatabase;
    }

    @Singleton
    @Provides
    MovieDao providesMovieDao(MovieDatabase movieDatabase) {
        return movieDatabase.getMovieDao();
    }

    @Singleton
    @Provides
    IMovieRepository movieRepository(MovieDao movieDao) {
        return new MovieRepository(movieDao);
    }
}
