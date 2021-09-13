package io.github.sergey_ivanenko.moviestorage.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sergey_ivanenko.moviestorage.MovieStorageApplication
import io.github.sergey_ivanenko.moviestorage.data.MovieRepository
import io.github.sergey_ivanenko.moviestorage.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val movieRepository: MovieRepository =
        MovieRepository(MovieStorageApplication.getDatabase())

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

    fun sortBySettings(sortBy: String): LiveData<List<Movie>> {
        return movieRepository.sortBySettings(sortBy)
    }
}