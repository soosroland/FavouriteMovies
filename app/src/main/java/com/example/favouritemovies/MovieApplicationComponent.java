package com.example.favouritemovies;


import android.app.Application;

import com.example.favouritemovies.interactor.MoviesInteractor;
import com.example.favouritemovies.network.NetworkModule;
import com.example.favouritemovies.persistence.MovieDatabase;
import com.example.favouritemovies.persistence.RoomModule;
import com.example.favouritemovies.persistence.dao.MovieDao;
import com.example.favouritemovies.persistence.repository.IMovieRepository;
import com.example.favouritemovies.ui.UIModule;
import com.example.favouritemovies.ui.movie.MovieActivity;
import com.example.favouritemovies.ui.movie.MoviePresenter;
import com.example.favouritemovies.ui.movies.MoviesFragment;
import com.example.favouritemovies.ui.movies.MoviesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, NetworkModule.class, AppModule.class, RoomModule.class})
public interface MovieApplicationComponent {

    void inject(MoviesPresenter moviesPresenter);

    void inject(MoviePresenter moviePresenter);

    void inject(MovieActivity movieActivity);

    void inject(MoviesFragment moviesFragment);

    void inject(MoviesInteractor moviesInteractor);

    MovieDao movieDao();

    MovieDatabase movieDatabase();

    IMovieRepository movieRepository();

    Application application();
}
