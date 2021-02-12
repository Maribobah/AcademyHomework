package ru.maribobah.academyhomework.data.localdb.entity

import android.provider.BaseColumns
import androidx.room.*

@Entity(
    tableName = "movies_by_category",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["_id"],
        childColumns = ["movie"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("movie")]
)
data class MoviesByCategoryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Long = 0,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "movie")
    val movie: Long

)

