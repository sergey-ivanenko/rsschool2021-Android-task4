package io.github.sergey_ivanenko.moviestorage

import android.app.Application
import androidx.room.Room
import io.github.sergey_ivanenko.moviestorage.data.dao.MovieSQLiteOpenHelper
import io.github.sergey_ivanenko.moviestorage.data.dao.RoomMovieDatabase

class MovieStorageApplication: Application() {

    private var roomDatabase: RoomMovieDatabase? = null
    private var sqliteDatabase: MovieSQLiteOpenHelper? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        roomDatabase = Room.databaseBuilder(
            this.applicationContext,
            RoomMovieDatabase::class.java,
            DB_NAME
        ).build()
        sqliteDatabase = MovieSQLiteOpenHelper(this)
    }

    companion object {
        fun getRoomDatabase(): RoomMovieDatabase {
            return INSTANCE?.roomDatabase as RoomMovieDatabase
        }

        fun getSQLiteDatabase(): MovieSQLiteOpenHelper {
            return INSTANCE?.sqliteDatabase as MovieSQLiteOpenHelper
        }

        private var INSTANCE: MovieStorageApplication? = null
        private const val DB_NAME = "movies_storage"
    }
}