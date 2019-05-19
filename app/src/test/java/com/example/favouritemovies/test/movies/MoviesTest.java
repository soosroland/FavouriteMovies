package com.example.favouritemovies.test.movies;

import com.example.favouritemovies.DaggerTestMovieComponent;
import com.example.favouritemovies.interactor.events.DeleteMovieEvent;
import com.example.favouritemovies.interactor.events.GetMoviesEvent;
import com.example.favouritemovies.interactor.events.UpdateMovieEvent;
import com.example.favouritemovies.model.MovieDto;
import com.example.favouritemovies.ui.movies.MoviesPresenter;
import com.example.favouritemovies.ui.movies.MoviesScreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import javax.inject.Inject;

import static com.example.favouritemovies.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class MoviesTest {
    @Inject
    MoviesPresenter moviesPresenter;

    MoviesScreen movieScreen;

    @Before
    public void Setup() {
        DaggerTestMovieComponent injector = setTestInjector();
        injector.inject(this);
        movieScreen = mock(MoviesScreen.class);
        moviesPresenter.attachScreen(movieScreen);
    }

    @Test
    public void Delete(){
        MovieDto testMovie = getTestMovie(1L);
        moviesPresenter.deleteMovie(testMovie, false);
        verify(movieScreen).remove(testMovie);
        verify(movieScreen).showMessage("Movie successfully deleted");
    }


    @Test
    public void DeleteWithNetworkError(){
        DeleteMovieEvent event = new DeleteMovieEvent();
        event.setCode(400);
        event.setThrowable(new Throwable("400 Bad request"));
        moviesPresenter.onDeleteEvent(event);
        verify(movieScreen).showNetworkError("400 Bad request");
    }

    @Test
    public void Update(){
        MovieDto testMovie = getTestMovie(2L);
        moviesPresenter.updateMovie(testMovie, false);
        verify(movieScreen).showMessage("Movie successfully saved");
    }

    @Test
    public void UpdateWithNetworkError(){
        UpdateMovieEvent event = new UpdateMovieEvent();
        event.setCode(400);
        event.setThrowable(new Throwable("400 Bad request"));
        moviesPresenter.onUpdateEvent(event);
        verify(movieScreen).showNetworkError("400 Bad request");
    }

    @Test
    public void ShowMovies(){
        moviesPresenter.showMoviesList(false);

        ArgumentCaptor<List<MovieDto>> moviesCaptor = ArgumentCaptor.forClass(List.class);
        verify(movieScreen).showMovies(moviesCaptor.capture());
        assertFalse(moviesCaptor.getValue().isEmpty());
        assertTrue(moviesCaptor.getValue().contains(getTestMovie(100L)));
    }

    @Test
    public void ShowMoviesWithNetworkError(){
        GetMoviesEvent event = new GetMoviesEvent();
        event.setCode(400);
        event.setThrowable(new Throwable("400 Bad request"));
        moviesPresenter.onGetMoviesEvent(event);
        verify(movieScreen).showNetworkError("400 Bad request");
    }

    @Test
    public void ShowOfflineMovies(){
        moviesPresenter.showMoviesList(true);

        ArgumentCaptor<List<MovieDto>> moviesCaptor = ArgumentCaptor.forClass(List.class);
        verify(movieScreen).showMovies(moviesCaptor.capture());
        assertFalse(moviesCaptor.getValue().isEmpty());
        assertEquals(getTestMovie(10L), moviesCaptor.getValue().get(0));
    }

    @After
    public void tearDown() {
        moviesPresenter.unRegister();
        moviesPresenter.detachScreen();
    }

    public MovieDto getTestMovie(Long id){
        MovieDto movieDto = new MovieDto();
        movieDto.setId(10L);
        movieDto.setTitle("Harry Potter 3");
        movieDto.setYear(2000);
        movieDto.setRating(8.1);
        return movieDto;
    }
}
