package com.example.favouritemovies;

import android.app.Application;
import android.provider.Settings;

import com.crashlytics.android.Crashlytics;
import com.example.favouritemovies.persistence.RoomModule;
import com.example.favouritemovies.ui.UIModule;
import io.fabric.sdk.android.Fabric;

public class MovieApplication extends Application {

    public static MovieApplicationComponent injector;
    protected static String androidId;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        injector =
                DaggerMovieApplicationComponent.builder().
                        appModule(
                                new AppModule(this)
                        ).roomModule(
                        new RoomModule(this)
                ).uIModule(
                        new UIModule(this)
                ).build();
    }

    public static String getAndroidId() {
        return androidId;
    }
}