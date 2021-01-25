package ru.maribobah.academyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MoviesListResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val movies: List<Movie>,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("total_pages")
    val totalPages: Int,
)