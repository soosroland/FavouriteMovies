package com.example.favouritemovies.ui.movie;

import com.example.favouritemovies.interactor.MoviesInteractor;
import com.example.favouritemovies.interactor.events.CreateMovieEvent;
import com.example.favouritemovies.model.MovieDto;
import com.example.favouritemovies.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class MoviePresenter extends Presenter<MovieScreen> {
    Executor networkExecutor;
    MoviesInteractor moviesInteractor;

    @Inject
    public MoviePresenter(Executor networkExecutor, MoviesInteractor moviesInteractor){
        this.networkExecutor = networkExecutor;
        this.moviesInteractor = moviesInteractor;

    }

    @Override
    public void attachScreen(MovieScreen screen){
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

//    public void createMovie(Long id, String title, double rating, int year, final boolean offlineMode){
//        final MovieDto movieDto = new MovieDto();
//        movieDto.setId(id);
//        movieDto.setTitle(title);
//        movieDto.setRating(rating);
//        movieDto.setYear(year);
//        networkExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                moviesInteractor.createMovie(movieDto, offlineMode);
//            }
//        });
//    }

    public void createMovie(String title, int year, double rating, final boolean offlineMode){
        final MovieDto movieDto = new MovieDto();
        movieDto.setTitle(title);
        movieDto.setYear(year);
        movieDto.setRating(rating);
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                moviesInteractor.createMovie(movieDto, offlineMode);
            }
        });
    }

    public void cancelCreate(){
        screen.forwardMovies();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final CreateMovieEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.forwardMovies();
            }
        }
    }
}
