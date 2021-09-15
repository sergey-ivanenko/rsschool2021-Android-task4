package io.github.sergey_ivanenko.moviestorage.data

import androidx.lifecycle.LiveData
import io.github.sergey_ivanenko.moviestorage.MovieStorageApplication
import io.github.sergey_ivanenko.moviestorage.data.dao.RoomMovieDatabase
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

class MovieRoomDataSourceImpl: MovieDataSource {

    private val db: RoomMovieDatabase = MovieStorageApplication.getRoomDatabase()
    private val movieDao get() = db.movieDao()

    override fun getAllMovies(): LiveData<List<Movie>> {
        return movieDao.getAllMovies()
    }

    override suspend fun addMovie(movie: Movie) {
        movieDao.addMovie(movie)
    }

    override suspend fun updateMovie(movie: Movie) {
        movieDao.updateMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    override fun sortBySettings(sortBy: String): LiveData<List<Movie>> {
        return movieDao.sortBySettings(sortBy)
    }
}