package com.example.favouritemovies.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "movies_table")
public class Movies {
    @PrimaryKey
    @NonNull
    private String Title;

    public Movies(@NonNull String title) {
        this.Title = title;
    }

    @NonNull
    public String getTitle() {
        return this.Title;
    }

    //Year, Rating, Id
}
