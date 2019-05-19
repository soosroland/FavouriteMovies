package com.example.favouritemovies.ui.movie;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.crashlytics.android.Crashlytics;
import com.example.favouritemovies.MovieApplication;
import com.example.favouritemovies.R;
import com.example.favouritemovies.ui.movies.MoviesActivity;

import javax.inject.Inject;

import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class MovieActivity extends AppCompatActivity implements MovieScreen, NavigationView.OnNavigationItemSelectedListener {
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_DESCRIPTION = "KEY_DESCRIPTION";

    @Inject
    MoviePresenter moviePresenter;

    private EditText etTitle;
    private EditText etYear;
    private EditText etRating;
    private AwesomeValidation mAwesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Move this to where you establish a user session
        logUser();

        setContentView(R.layout.activity_movie);
        MovieApplication.injector.inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar_movie);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_movie);
        NavigationView navigationView = findViewById(R.id.nav_view_movie);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
        mAwesomeValidation.setContext(this);
        mAwesomeValidation.addValidation(this, R.id.etTitle, "^(?=\\s*\\S).*$", R.string.name_required);

        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        etRating = findViewById(R.id.etRating);

        Button cancel = findViewById(R.id.btnCancel);
        Button save = findViewById(R.id.btnSave);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moviePresenter.cancelCreate();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAwesomeValidation.validate()){
                    int year=0;
                    String yeartext = etYear.getText().toString();
                    if(!yeartext.isEmpty())
                        try
                        {
                            year= Integer.parseInt(yeartext);
                            // it means it is double
                        } catch (Exception e1) {
                            // this means it is not double
                            e1.printStackTrace();
                        }
                    double rating=0;
                    String ratingtext = etRating.getText().toString();
                    if(!ratingtext.isEmpty())
                        try
                        {
                            rating= Double.parseDouble(ratingtext);
                            // it means it is double
                        } catch (Exception e1) {
                            // this means it is not double
                            e1.printStackTrace();
                        }
                    moviePresenter.createMovie(etTitle.getText().toString(), year, rating, !isNetworkAvailable());
                }
            }
        });
    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStart() {
        super.onStart();
        moviePresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        moviePresenter.detachScreen();
    }

    @Override
    public void forwardMovies() {
        Intent intent = new Intent(MovieActivity.this, MoviesActivity.class);
        startActivity(intent);
    }

    @Override
    public void showNetworkError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_movie);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_movies) {
            Intent intent = new Intent(MovieActivity.this, MovieActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_movie);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}
