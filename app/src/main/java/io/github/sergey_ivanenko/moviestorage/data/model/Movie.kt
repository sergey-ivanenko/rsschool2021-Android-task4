package io.github.sergey_ivanenko.moviestorage.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.sergey_ivanenko.moviestorage.data.model.Movie.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val year: Int,
    val genre: String
): Parcelable{
    companion object {
        const val TABLE_NAME = "movies_table"
    }
}