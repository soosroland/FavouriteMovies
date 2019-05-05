package com.example.favouritemovies.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MoviesDao {
    @Query("SELECT * from movies_table ORDER BY title ASC")
    LiveData<List<Movies>> getAlphabetizedWords();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movies movies);

    @Query("DELETE FROM movies_table")
    void deleteAll();
}
