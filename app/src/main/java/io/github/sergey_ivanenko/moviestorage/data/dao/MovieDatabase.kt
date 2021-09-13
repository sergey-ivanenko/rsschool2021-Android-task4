package io.github.sergey_ivanenko.moviestorage.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.sergey_ivanenko.moviestorage.data.dao.MovieDatabase.Companion.DB_VERSION
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

@Database(entities = [Movie::class], version = DB_VERSION/*, exportSchema = false*/)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val DB_VERSION = 1
    }
}