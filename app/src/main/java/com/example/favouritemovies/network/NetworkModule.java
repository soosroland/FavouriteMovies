package com.example.favouritemovies.network;

import javax.inject.Singleton;

import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create());

    }

    @Provides
    @Singleton
    public MoviesApi provideArtistsApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(NetworkConfig.ENDPOINT_ADDRESS).build().create(MoviesApi.class);
    }

    @Provides
    @Singleton
    public TokenApi provideTokenApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(NetworkConfig.TOKEN_ENDPOINT_ADDRESS).build().create(TokenApi.class);
    }
}
