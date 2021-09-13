package io.github.sergey_ivanenko.moviestorage

import android.app.Application
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

    companion object {
        fun getDatabase(): MovieDatabase {
            return INSTANCE?.database as MovieDatabase
        }

        private var INSTANCE: MovieStorageApplication? = null
        private const val DB_NAME = "movies_storage"
    }
}