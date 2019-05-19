package com.example.favouritemovies.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "movie")
public class MovieDto {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private Long id = null;

    @NonNull
    @SerializedName("title")
    private String title = null;

    @SerializedName("year")
    private Integer year = null;

    @SerializedName("rating")
    private Double rating = null;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDto movieDto = (MovieDto) o;
        return Objects.equals(id, movieDto.id) &&
                Objects.equals(title, movieDto.title) &&
                Objects.equals(year, movieDto.year) &&
                Objects.equals(rating, movieDto.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, rating);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MovieDto {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    year: ").append(toIndentedString(year)).append("\n");
        sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
