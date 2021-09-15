package io.github.sergey_ivanenko.moviestorage.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class MovieSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
) {

    /*var db: SQLiteDatabase? = null*/

    // this is called the first time a database is accessed. There should be code in here to create a new database.
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(100), year INT, genre VARCHAR(100))"

        db?.execSQL(createTableStatement)
    }

    // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    private fun getCursorWithMovies(): Cursor {
        val stringQuery = "SELECT * FROM $TABLE_NAME"

        return readableDatabase.rawQuery(stringQuery, null)
    }

    fun getAllMovies(): LiveData<List<Movie>> {
        /*val stringQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = readableDatabase.rawQuery(stringQuery, null)
        cursor.use { it ->
            if (it.moveToFirst()) {
                do {
                    val title = it.getString(it.getColumnIndex(TITLE_COLUMN))
                } while (it.moveToNext())
            }
        }*/
        val listOfMovies = mutableListOf<Movie>()
        getCursorWithMovies().use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val id: Int = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COLUMN))
                    val title: String = cursor.getString(cursor.getColumnIndexOrThrow(TITLE_COLUMN))
                    val year: Int = cursor.getInt(cursor.getColumnIndexOrThrow(YEAR_COLUMN))
                    val genre: String = cursor.getString(cursor.getColumnIndexOrThrow(GENRE_COLUMN))

                    listOfMovies.add(Movie(id, title, year, genre))
                } while (cursor.moveToNext())
            }
        }

        val liveDataOfMovies = MutableLiveData<List<Movie>>()
        liveDataOfMovies.value = listOfMovies

        return liveDataOfMovies
    }

    fun addMovie(movie: Movie) {
        val db: SQLiteDatabase = this.writableDatabase

        val values = ContentValues().apply {
            put(TITLE_COLUMN, movie.title)
            put(YEAR_COLUMN, movie.year)
            put(GENRE_COLUMN, movie.genre)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateMovie(movie: Movie) {
        val db: SQLiteDatabase = this.writableDatabase

        val updateQuery = "UPDATE $TABLE_NAME SET " +
                "$TITLE_COLUMN = ${movie.title}, " +
                "$YEAR_COLUMN = ${movie.year}, " +
                "$GENRE_COLUMN = ${movie.genre} WHERE $ID_COLUMN = ${movie.id}"

        db.execSQL(updateQuery)
        db.close()
    }

    fun deleteMovie(movie: Movie) {
        val db: SQLiteDatabase = this.writableDatabase
        val selection = "$ID_COLUMN = ?"
        val selectionArgs = arrayOf(movie.id.toString())
        val deletedRows = db.delete(TABLE_NAME, selection, selectionArgs)

        db.close()
    }

    fun sortBySettings(sortBy: String): LiveData<List<Movie>> {
        val listOfMovies = mutableListOf<Movie>()
        /*val sortByQuery = "SELECT * FROM ${Movie.TABLE_NAME} ORDER BY CASE " +
                "WHEN :sortBy = 'id' THEN id " +
                "WHEN :sortBy = 'title' THEN title " +
                "WHEN :sortBy = 'year' THEN year " +
                "WHEN :sortBy = 'genre' THEN genre END"*/
        val sortByQuery = "SELECT * FROM ${Movie.TABLE_NAME} ORDER BY $sortBy"

        val cursor = readableDatabase.rawQuery(sortByQuery, null)
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val id: Int = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COLUMN))
                    val title: String = cursor.getString(cursor.getColumnIndexOrThrow(TITLE_COLUMN))
                    val year: Int = cursor.getInt(cursor.getColumnIndexOrThrow(YEAR_COLUMN))
                    val genre: String = cursor.getString(cursor.getColumnIndexOrThrow(GENRE_COLUMN))

                    listOfMovies.add(Movie(id, title, year, genre))
                } while (it.moveToNext())
            }
        }
        cursor.close()

        val liveDataOfMovies = MutableLiveData<List<Movie>>()
        liveDataOfMovies.value = listOfMovies

        return liveDataOfMovies
    }

    companion object {
        private const val DB_NAME = "movies_storage"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "movies_table"
        private const val ID_COLUMN = "id"
        private const val TITLE_COLUMN = "title"
        private const val YEAR_COLUMN = "year"
        private const val GENRE_COLUMN = "genre"
        private const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}