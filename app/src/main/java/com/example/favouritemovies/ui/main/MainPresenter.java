package com.example.favouritemovies.ui.main;

import com.example.favouritemovies.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    public MainPresenter() {
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showArtistsSearchList(
            String artistSearchTerm) {
        screen.showArtists(artistSearchTerm);
    }
}
