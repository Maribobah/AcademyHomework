package ru.maribobah.academyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Movie(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val name: String,
    @SerialName("adult")
    val adult: Boolean,
    var rate: String = "",
    @SerialName("like")
    val like: Boolean = false,
    @SerialName("vote_average")
    var stars: Float,
    @SerialName("vote_count")
    val votesCount: Int,
    var reviews: String = "",
    @SerialName("runtime")
    val runtime: Int = 0,
    var duration: String = "",
    @SerialName("poster_path")
    var poster: String?,
    @SerialName("backdrop_path")
    var backdrop: String?,
    @SerialName("overview")
    val storyline: String,
    @SerialName("genres")
    val genres: List<Genre> = listOf(),
    var genresPresentation: String = ""
)
