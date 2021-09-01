package io.github.sergey_ivanenko.moviestorage.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.sergey_ivanenko.moviestorage.data.dao.MovieDatabase.Companion.DB_VERSION
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

@Database(entities = [Movie::class], version = DB_VERSION, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {/*movies_storage*/

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            val currentInstance = INSTANCE
            if (currentInstance != null) {
                return currentInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        const val DB_VERSION = 1
        private const val DB_NAME = "movies_storage"
    }
}