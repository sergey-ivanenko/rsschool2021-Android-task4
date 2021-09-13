package io.github.sergey_ivanenko.moviestorage.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.sergey_ivanenko.moviestorage.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${Movie.TABLE_NAME}")/* ORDER BY id ASC*/
    fun getAllMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM ${Movie.TABLE_NAME} ORDER BY CASE " +
            "WHEN :sortBy = 'id' THEN id " +
            "WHEN :sortBy = 'title' THEN title " +
            "WHEN :sortBy = 'year' THEN year " +
            "WHEN :sortBy = 'genre' THEN genre END")
    fun sortBySettings(sortBy: String): LiveData<List<Movie>>
}