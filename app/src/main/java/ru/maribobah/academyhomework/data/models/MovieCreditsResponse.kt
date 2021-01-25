package ru.maribobah.academyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MovieCreditsResponse (
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val actors: List<Actor>,
)