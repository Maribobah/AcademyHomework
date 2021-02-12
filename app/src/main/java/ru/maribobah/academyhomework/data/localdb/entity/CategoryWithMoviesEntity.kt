package ru.maribobah.academyhomework.data.localdb.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithMoviesEntity(
    @Embedded
    val moviesByCategory: MoviesByCategoryEntity,
    @Relation(
        parentColumn = "movie",
        entityColumn = "_id"
    )
    val movies: List<MovieEntity>
)