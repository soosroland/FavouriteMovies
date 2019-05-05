package com.example.favouritemovies.network;

import android.media.session.MediaSession;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TokenApi {
    @POST("token")
    @FormUrlEncoded
    Call<MediaSession.Token> getToken(@Field("grant_type") String grantType, @Header("Authorization") String authorisation);
}