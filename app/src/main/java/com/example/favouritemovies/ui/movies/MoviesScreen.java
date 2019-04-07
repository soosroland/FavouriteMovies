package com.example.favouritemovies.ui.movies;


import android.content.ClipData;

import java.util.List;

public interface MoviesScreen {
    void showArtists(List<ClipData.Item> artists);

    void showNetworkError(String errorMsg);
}
