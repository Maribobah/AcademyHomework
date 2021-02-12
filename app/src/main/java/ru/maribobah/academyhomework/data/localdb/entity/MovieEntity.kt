package ru.maribobah.academyhomework.data.localdb.entity

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "movies"
)
data class MovieEntity(

    @PrimaryKey
    @ColumnInfo(name = BaseColumns._ID)
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val name: String,

    @ColumnInfo(name = "rate")
    val rate: String,

    @ColumnInfo(name = "like")
    val like: Boolean,

    @ColumnInfo(name = "stars")
    val stars: Float,

    @ColumnInfo(name = "reviews")
    val reviews: String,

    @ColumnInfo(name = "duration")
    val duration: String,

    @ColumnInfo(name = "poster")
    val poster: String,

    @ColumnInfo(name = "backdrop")
    val backdrop: String,

    @ColumnInfo(name = "storyline")
    val storyline: String,

    @ColumnInfo(name = "genres")
    val genres: String

)