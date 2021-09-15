package io.github.sergey_ivanenko.moviestorage.data

import androidx.lifecycle.LiveData
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

interface MovieDataSource {

    fun getAllMovies(): LiveData<List<Movie>>
    suspend fun addMovie(movie: Movie)
    suspend fun updateMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    fun sortBySettings(sortBy: String): LiveData<List<Movie>>
}