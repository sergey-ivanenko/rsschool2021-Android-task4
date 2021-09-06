package io.github.sergey_ivanenko.moviestorage

import android.app.Application
import android.content.Context
import androidx.room.Room
import io.github.sergey_ivanenko.moviestorage.data.dao.MovieDatabase

class MovieStorageApplication: Application() {

    private var database: MovieDatabase? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        database = Room.databaseBuilder(
            this.applicationContext,
            MovieDatabase::class.java,
            DB_NAME
        ).build()
    }

    fun getDatabase(): MovieDatabase? {
        return database
    }

    companion object {

        private var INSTANCE: MovieStorageApplication? = null
        private const val DB_NAME = "movies_storage"
    }

    /*
    public static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
     */
}