package com.example.favouritemovies.persistence;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.favouritemovies.model.MovieDto;
import com.example.favouritemovies.persistence.dao.MovieDao;


@Database(entities = {MovieDto.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();


    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile MovieDatabase INSTANCE;


    public static MovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "movie.db")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.

            //new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MovieDao tDao;

        PopulateDbAsync(MovieDatabase db) {
            tDao = db.getMovieDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            tDao.deleteAll();

            /*MovieDto movie = new MovieDto();
            long id=0;
            movie.setId(id);
            movie.setTitle("Harry Potter 1");
            movie.setYear(1997);
            movie.setRating(9.1);
            tDao.insert(movie);
            id++;
            movie.setId(id);
            movie.setTitle("Harry Potter 2");
            movie.setYear(1999);
            movie.setRating(8.9);
            tDao.insert(movie);*/

            return null;
        }
    }


}
