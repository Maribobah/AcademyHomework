package ru.maribobah.academyhomework.data.localdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maribobah.academyhomework.data.localdb.DbContract


@Entity(
    tableName = DbContract.Movies.TABLE_NAME
)
data class MovieEntity(

    @PrimaryKey
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_TITLE)
    val name: String,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_RATE)
    val rate: String,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_LIKE)
    val like: Boolean,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_STARS)
    val stars: Float,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_REVIEWS)
    val reviews: String,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_DURATION)
    val duration: String,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_POSTER)
    val poster: String,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_BACKDROP)
    val backdrop: String,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_STORYLINE)
    val storyline: String,

    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_GENRES)
    val genres: String

)