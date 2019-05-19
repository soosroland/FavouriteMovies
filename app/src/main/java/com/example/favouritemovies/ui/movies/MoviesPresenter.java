package com.example.favouritemovies.ui.movies;

import com.example.favouritemovies.interactor.MoviesInteractor;
import com.example.favouritemovies.interactor.events.BaseEvent;
import com.example.favouritemovies.interactor.events.DeleteMovieEvent;
import com.example.favouritemovies.interactor.events.GetMoviesEvent;
import com.example.favouritemovies.interactor.events.UpdateMovieEvent;
import com.example.favouritemovies.model.MovieDto;
import com.example.favouritemovies.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class MoviesPresenter extends Presenter<MoviesScreen> {
    Executor networkExecutor;
    MoviesInteractor moviesInteractor;

    @Inject
    public MoviesPresenter(Executor executor, MoviesInteractor moviesInteractor) {
        this.moviesInteractor = moviesInteractor;
        this.networkExecutor = executor;
    }

    @Override
    public void attachScreen(MoviesScreen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void unRegister(){
        EventBus.getDefault().unregister(this);
    }

    public void showMoviesList(final boolean offlineMode){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                moviesInteractor.getMovies(offlineMode);
            }
        });
    }

    public void deleteMovie(final MovieDto movieDto, final boolean offlineMode){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                moviesInteractor.deleteMovie(movieDto, offlineMode);
            }
        });

    }


    public void updateMovie(final MovieDto movieDto, final boolean offlineMode){
       /* boolean updatedValue = !movieDto.getIsDone();
        movieDto.setIsDone(updatedValue);*/
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                moviesInteractor.updateMovie(movieDto, offlineMode);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMoviesEvent(final GetMoviesEvent event) {
        if (event.getThrowable() != null) {
            handleError(event);
        } else {
            if (screen != null) {
                screen.showMovies(event.getMovieDtoList());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateEvent(final UpdateMovieEvent event) {
        if (event.getThrowable() != null) {
            handleError(event);
        } else {
            if (screen != null) {
                screen.showMessage("Movie successfully saved");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteEvent(final DeleteMovieEvent event) {
        if (event.getThrowable() != null) {
            handleError(event);
        } else {
            if (screen != null) {
                screen.remove(event.getRemovedMovieDto());
                screen.showMessage("Movie successfully deleted");
            }
        }
    }

    private void handleError(BaseEvent event) {
        event.getThrowable().printStackTrace();
        if (screen != null) {
            screen.showNetworkError(event.getThrowable().getMessage());
        }
    }
}
