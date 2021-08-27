package io.github.sergey_ivanenko.moviestorage.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import kotlinx.parcelize.Parcelize

@Parcelize
// For Room annotation
@Entity(tableName = "movies_table")
// For ORMLite annotation
@DatabaseTable(tableName = "movies_table")
data class Movie(

    // For ORMLite annotation
    @DatabaseField(generatedId = true)
    // For Room annotation
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @DatabaseField
    val title: String,

    @DatabaseField
    val year: Int,

    @DatabaseField
    val genre: String
): Parcelable {
    constructor(): this(0, "", 0, "")
}