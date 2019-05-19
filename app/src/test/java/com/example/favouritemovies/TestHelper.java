package com.example.favouritemovies;

import com.example.favouritemovies.mock.MockRoomModule;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

public class TestHelper {
    public static DaggerTestMovieComponent setTestInjector() {
        ShadowLog.stream = System.out;
        MovieApplication application = (MovieApplication) RuntimeEnvironment.application;
        MovieApplicationComponent injector = DaggerTestMovieComponent.builder().
                mockRoomModule(new MockRoomModule()).
                testAppModule(new TestAppModule(application)).
                testModule(new TestModule(application.getApplicationContext())).build();
        application.injector = injector;
        application.androidId = "DEVICE_ID";
        return (DaggerTestMovieComponent) injector;
    }
}
