package com.example.favouritemovies.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.favouritemovies.model.MovieDto;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    public void insert(MovieDto... movies);

    @Update
    public void update(MovieDto... movies);

    @Delete
    public void delete(MovieDto movie);

    @Query("Select * FROM movie")
    public List<MovieDto> getAll();

    @Query("Delete FROM movie")
    public void deleteAll();
}
