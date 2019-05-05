package com.example.favouritemovies.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
