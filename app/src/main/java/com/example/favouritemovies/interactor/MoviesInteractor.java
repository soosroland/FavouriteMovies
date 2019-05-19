package com.example.favouritemovies.interactor;

import com.example.favouritemovies.MovieApplication;
import com.example.favouritemovies.interactor.events.CreateMovieEvent;
import com.example.favouritemovies.interactor.events.DeleteMovieEvent;
import com.example.favouritemovies.interactor.events.GetMoviesEvent;
import com.example.favouritemovies.interactor.events.UpdateMovieEvent;
import com.example.favouritemovies.model.MovieDto;
import com.example.favouritemovies.network.MovieApi;
import com.example.favouritemovies.persistence.repository.IMovieRepository;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class MoviesInteractor {
    MovieApi movieApi;
    IMovieRepository movieRepository;

    @Inject
    public MoviesInteractor(MovieApi movieApi, IMovieRepository repository){
        this.movieApi = movieApi;
        this.movieRepository = repository;
        MovieApplication.injector.inject(this);
    }

    public void getMovies(boolean offlineMode){
        GetMoviesEvent event = new GetMoviesEvent();
        try {
            if(offlineMode){
                event.setCode(200);
                event.setMovieDtoList(movieRepository.getAll());
            }else {
                Call<List<MovieDto>> getMoviesQuerryCall = movieApi.getMovies(MovieApplication.getAndroidId());
                Response<List<MovieDto>> response = getMoviesQuerryCall.execute();
                if (response.code() != 200) {
                    throw new Exception("Result code is not 200! "+response.code());
                }
                event.setCode(response.code());
                event.setMovieDtoList(response.body());
            }
        }catch (Exception e){
            event.setThrowable(e);
        }
        EventBus.getDefault().post(event);
    }

    public void createMovie(MovieDto movieDto, boolean offlineMode){
        CreateMovieEvent event = new CreateMovieEvent(movieDto);
        event.setCode(200);
        try {
            if(!offlineMode) {
                Call<Void> addMovieCall = movieApi.addMovie(movieDto, MovieApplication.getAndroidId());
                Response<Void> response = addMovieCall.execute();
                if (response.code() != 200) {
                    throw new Exception("Result code is not 200");
                }
                event.setCode(response.code());
            }
            movieRepository.insert(movieDto);
        }catch (Exception e){
            event.setThrowable(e);
        }
        EventBus.getDefault().post(event);
    }

    public void updateMovie(MovieDto movieDto, boolean offlineMode) {
        UpdateMovieEvent event = new UpdateMovieEvent(movieDto);
        event.setCode(200);
        try {
            if(!offlineMode) {
                Call<Void> updateMovieCall = movieApi.updateMovie(movieDto, MovieApplication.getAndroidId());
                Response<Void> response = updateMovieCall.execute();
                if (response.code() != 200) {
                    throw new Exception("Result code is not 200");
                }
                event.setCode(response.code());
            }
            movieRepository.update(movieDto);
        }catch (Exception e){
            event.setThrowable(e);
        }
        EventBus.getDefault().post(event);
    }

    public void deleteMovie(MovieDto movieDto, boolean offlineMode){
        DeleteMovieEvent event = new DeleteMovieEvent();
        event.setCode(200);
        try {
            if(!offlineMode) {
                Call<Void> deleteMovieCall = movieApi.deleteMovie(MovieApplication.getAndroidId(), movieDto.getId());
                Response<Void> response = deleteMovieCall.execute();
                if (response.code() != 200) {
                    throw new Exception("Result code is not 200");
                }
                event.setCode(response.code());
            }
            event.setRemovedMovieDto(movieDto);
            movieRepository.delete(movieDto);
        }catch (Exception e){
            event.setThrowable(e);
        }
        EventBus.getDefault().post(event);
    }
}
