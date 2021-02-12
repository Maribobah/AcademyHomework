package ru.maribobah.academyhomework.data.localdb.entity

import android.provider.BaseColumns
import androidx.room.*

@Entity(
    tableName = "actors_by_movie",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["_id"],
            childColumns = ["movie"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["_id"],
            childColumns = ["actor"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("movie"),
        Index("actor")
    ]
)
data class ActorsByMovieEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Long = 0,

    @ColumnInfo(name = "movie")
    val movie: Long,

    @ColumnInfo(name = "actor")
    val actor: Long
)

