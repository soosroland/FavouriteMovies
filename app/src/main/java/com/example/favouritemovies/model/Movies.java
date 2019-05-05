package com.example.favouritemovies.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies_table")
public class Movies {
    @PrimaryKey
    @NonNull
    private String Id;
    private String Title;
    private Integer Year;
    private Double Rating;

    public Movies(@NonNull String Id) {
        this.Id = Id;
    }

    @NonNull
    public String getId() {
        return this.Id;
    }

    public String getTitle() {
        return this.Title;
    }

    public Integer getYear() {
        return this.Year;
    }

    public Double getRating() { return this.Rating; }

    public void setTitle(String title) {
        Title = title;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }
}
