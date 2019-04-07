package com.example.favouritemovies;

import android.app.Application;

public class FavouriteMoviesApplication extends Application {

    //public static FavouriteMoviesApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        /*injector =
                DaggerFavouriteMoviesApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();*/
    }
}