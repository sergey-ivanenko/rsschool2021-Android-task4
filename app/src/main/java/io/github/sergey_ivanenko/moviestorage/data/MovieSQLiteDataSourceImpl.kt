package io.github.sergey_ivanenko.moviestorage.data

import androidx.lifecycle.LiveData
import io.github.sergey_ivanenko.moviestorage.MovieStorageApplication
import io.github.sergey_ivanenko.moviestorage.data.dao.MovieSQLiteOpenHelper
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class MovieSQLiteDataSourceImpl: MovieDataSource {

    private val dbHelper: MovieSQLiteOpenHelper = MovieStorageApplication.getSQLiteDatabase()

    override fun getAllMovies(): LiveData<List<Movie>> {
        return dbHelper.getAllMovies()
    }

    override suspend fun addMovie(movie: Movie) {
        dbHelper.addMovie(movie)
    }

    override suspend fun updateMovie(movie: Movie) {
        dbHelper.updateMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        dbHelper.deleteMovie(movie)
    }

    override fun sortBySettings(sortBy: String): LiveData<List<Movie>> {
        return dbHelper.sortBySettings(sortBy)
    }
}