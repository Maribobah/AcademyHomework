package ru.maribobah.academyhomework.data.localdb.entity

import androidx.room.*
import ru.maribobah.academyhomework.data.localdb.DbContract

@Entity(
    tableName = DbContract.MoviesByCategory.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = [DbContract.Movies.COLUMN_NAME_ID],
        childColumns = [DbContract.MoviesByCategory.COLUMN_NAME_MOVIE],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(DbContract.MoviesByCategory.COLUMN_NAME_MOVIE)]
)
data class MoviesByCategory(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.MoviesByCategory.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.MoviesByCategory.COLUMN_NAME_CATEGORY)
    val category: String,

    @ColumnInfo(name = DbContract.MoviesByCategory.COLUMN_NAME_MOVIE)
    val movie: Long

)

data class CategoryWithMovies(
    @Embedded
    val moviesByCategory: MoviesByCategory,
    @Relation(
        parentColumn = DbContract.MoviesByCategory.COLUMN_NAME_MOVIE,
        entityColumn = DbContract.Movies.COLUMN_NAME_ID
    )
    val movies: List<MovieEntity>
)