package io.github.sergey_ivanenko.moviestorage.data

import androidx.lifecycle.LiveData
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class MovieRepository(
    private val movieRoomDataSource: MovieDataSource/*,
    private val movieSQLiteDataSource: MovieSQLiteDataSource*/
) {

    fun getAllMovies(): LiveData<List<Movie>> = movieRoomDataSource.getAllMovies()

    suspend fun addMovie(movie: Movie) {
        movieRoomDataSource.addMovie(movie)
    }

    suspend fun updateMovie(movie: Movie) {
        movieRoomDataSource.updateMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieRoomDataSource.deleteMovie(movie)
    }

    fun sortBySettings(sortBy: String): LiveData<List<Movie>> {
        return movieRoomDataSource.sortBySettings(sortBy)
    }
}

/*
class MovieRepository(private val db: RoomMovieDatabase) {

    private val movieDao get() = db.movieDao()

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

    fun sortBySettings(sortBy: String): LiveData<List<Movie>> {
        return movieDao.sortBySettings(sortBy)
    }
}*/
