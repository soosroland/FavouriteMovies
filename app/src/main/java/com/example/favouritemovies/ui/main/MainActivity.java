package com.example.favouritemovies.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.favouritemovies.ui.movies.MoviesActivity;

public class MainActivity extends AppCompatActivity implements MainScreen {

    public static final String KEY_ARTIST = "KEY_ARTIST";

    MainPresenter mainPresenter;
    private EditText etArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void showArtists(String artistSearchTerm) {
        Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
        intent.putExtra(KEY_ARTIST, artistSearchTerm);
        startActivity(intent);
    }
}
