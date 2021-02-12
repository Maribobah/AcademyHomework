package ru.maribobah.academyhomework.data.localdb.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithActorsEntity(
    @Embedded
    val actorsByMovie: ActorsByMovieEntity,
    @Relation(
        parentColumn = "actor",
        entityColumn = "_id"
    )
    val actors: List<ActorEntity>
)