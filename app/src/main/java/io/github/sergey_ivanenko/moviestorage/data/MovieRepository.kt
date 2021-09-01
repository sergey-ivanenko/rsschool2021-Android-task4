package io.github.sergey_ivanenko.moviestorage.data

import androidx.lifecycle.LiveData
import io.github.sergey_ivanenko.moviestorage.data.dao.MovieDao
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class MovieRepository(private val movieDao: MovieDao/*private val db: MovieDatabase*/) {

    /*private val movieDao get() = db.movieDao()*/

    fun getAllMovies(): LiveData<List<Movie>> {
        return movieDao.getAllMovies()
    }

    suspend fun addMovie(movie: Movie) {
        movieDao.addMovie(movie)
    }

    suspend fun updateMovie(movie: Movie) {
        movieDao.updateMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }
}