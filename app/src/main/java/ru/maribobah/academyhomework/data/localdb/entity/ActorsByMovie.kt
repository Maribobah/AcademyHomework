package ru.maribobah.academyhomework.data.localdb.entity

import androidx.room.*
import ru.maribobah.academyhomework.data.localdb.DbContract

@Entity(
    tableName = DbContract.ActorsByMovie.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = [DbContract.Movies.COLUMN_NAME_ID],
            childColumns = [DbContract.ActorsByMovie.COLUMN_NAME_MOVIE],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = [DbContract.Actors.COLUMN_NAME_ID],
            childColumns = [DbContract.ActorsByMovie.COLUMN_NAME_ACTOR],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(DbContract.ActorsByMovie.COLUMN_NAME_MOVIE),
        Index(DbContract.ActorsByMovie.COLUMN_NAME_ACTOR)
    ]
)
data class ActorsByMovie(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.ActorsByMovie.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.ActorsByMovie.COLUMN_NAME_MOVIE)
    val movie: Long,

    @ColumnInfo(name = DbContract.ActorsByMovie.COLUMN_NAME_ACTOR)
    val actor: Long
)

data class MovieWithActors(
    @Embedded
    val actorsByMovie: ActorsByMovie,
    @Relation(
        parentColumn = DbContract.ActorsByMovie.COLUMN_NAME_ACTOR,
        entityColumn = DbContract.Actors.COLUMN_NAME_ID
    )
    val actors: List<ActorEntity>
)
