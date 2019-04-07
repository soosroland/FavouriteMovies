package com.example.favouritemovies.ui;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    public Context provideContext() {
        return context;
    }

    public Executor provideNetworkExecutor() {
        return null;
    }
}