package com.example.favouritemovies.test.movie;

import com.example.favouritemovies.DaggerTestMovieComponent;
import com.example.favouritemovies.interactor.events.CreateMovieEvent;
import com.example.favouritemovies.ui.movie.MoviePresenter;
import com.example.favouritemovies.ui.movie.MovieScreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import javax.inject.Inject;

import static com.example.favouritemovies.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
public class MovieTest {
    @Inject
    MoviePresenter moviePresenter;

    private MovieScreen movieScreen;

    @Before
    public void Setup() {
        DaggerTestMovieComponent injector = setTestInjector();
        injector.inject(this);
        movieScreen = mock(MovieScreen.class);
        moviePresenter.attachScreen(movieScreen);
    }

    @Test
    public void Create(){
        moviePresenter.createMovie("Harry Potter and the Philosopher's",2005, 8.1, false);
        verify(movieScreen).forwardMovies();
    }

    @Test
    public void CreateWithNoTitle(){
        moviePresenter.createMovie("",2005, 8.1, false);
        verify(movieScreen).forwardMovies();
    }

    @Test
    public void Cancel(){
        moviePresenter.cancelCreate();
        verify(movieScreen).forwardMovies();
    }

    @Test
    public void NetworkError(){
        CreateMovieEvent event = new CreateMovieEvent();
        event.setCode(400);
        event.setThrowable(new Throwable("400 Bad request"));
        moviePresenter.onEventMainThread(event);
        verify(movieScreen).showNetworkError("400 Bad request");
    }

    @After
    public void tearDown() {
        moviePresenter.detachScreen();
    }
}
