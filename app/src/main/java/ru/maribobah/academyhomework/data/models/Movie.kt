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
    @SerialName("like")
    val like: Boolean = false,
    @SerialName("vote_average")
    val stars: Float,
    @SerialName("vote_count")
    val reviews: Int,
    @SerialName("runtime")
    val duration: Int = 0,
    @SerialName("poster_path")
    var poster: String?,
    @SerialName("backdrop_path")
    var backdrop: String?,
    @SerialName("overview")
    val storyline: String,
    @SerialName("genres")
    val genres: List<Genre> = listOf<Genre>()
)
