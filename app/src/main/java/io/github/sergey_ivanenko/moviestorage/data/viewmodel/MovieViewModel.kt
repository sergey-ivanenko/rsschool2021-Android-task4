package io.github.sergey_ivanenko.moviestorage.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import io.github.sergey_ivanenko.moviestorage.data.MovieRepository
import io.github.sergey_ivanenko.moviestorage.data.dao.MovieDatabase
import io.github.sergey_ivanenko.moviestorage.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDao = MovieDatabase.getDatabase(application).movieDao()
    private val movieRepository: MovieRepository = MovieRepository(movieDao)

    val getAllMovies: LiveData<List<Movie>> = movieRepository.getAllMovies()

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
}