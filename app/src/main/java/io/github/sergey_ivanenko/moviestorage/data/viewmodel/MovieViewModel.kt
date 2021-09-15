package io.github.sergey_ivanenko.moviestorage.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sergey_ivanenko.moviestorage.data.MovieRepository
import io.github.sergey_ivanenko.moviestorage.data.MovieDataSource
import io.github.sergey_ivanenko.moviestorage.data.MovieRoomDataSourceImpl
import io.github.sergey_ivanenko.moviestorage.data.MovieSQLiteDataSourceImpl
import io.github.sergey_ivanenko.moviestorage.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    /*private val movieRepository: MovieRepository =
        MovieRepository(MovieStorageApplication.getDatabase())*/

    private var movieRepository: MovieRepository =
        MovieRepository(MovieRoomDataSourceImpl())

    private var movieRoomDataSource: MovieDataSource? = null
    private var movieSQLiteDataSource: MovieDataSource? = null

    val getAllMovies: LiveData<List<Movie>> = movieRepository.getAllMovies()

    fun changeMovieDataSource(isUseCursor: Boolean? = false) {
        if (isUseCursor == null || !isUseCursor) {
            movieRoomDataSource = MovieRoomDataSourceImpl()
            movieRepository = MovieRepository(movieRoomDataSource as MovieRoomDataSourceImpl)
        } else {
            movieSQLiteDataSource = MovieSQLiteDataSourceImpl()
            movieRepository = MovieRepository(movieSQLiteDataSource as MovieSQLiteDataSourceImpl)
        }
    }

    fun addMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.addMovie(movie)
        }
    }

    fun updateMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.updateMovie(movie)
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.deleteMovie(movie)
        }
    }

    fun sortBySettings(sortBy: String): LiveData<List<Movie>> {
        return movieRepository.sortBySettings(sortBy)
    }
}