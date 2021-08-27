package io.github.sergey_ivanenko.moviestorage.data.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class DatabaseHelper(context: Context) :
    OrmLiteSqliteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    private var movieDao: Dao<Movie, Int>? = null

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Movie::class.java)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int,
    ) {
        TableUtils.dropTable<Movie, Any>(connectionSource, Movie::class.java, true)
        onCreate(database, connectionSource)
    }

    fun getMovieDao(): Dao<Movie, Int> {
        if (movieDao != null) {
            return movieDao as Dao<Movie, Int>
        }

        return super.getDao(Movie::class.java)
    }

    override fun close() {
        super.close()
        movieDao = null
    }

    companion object {
        private const val DB_NAME = "movies_storage.db"
        private const val DB_VERSION = 1
    }
}