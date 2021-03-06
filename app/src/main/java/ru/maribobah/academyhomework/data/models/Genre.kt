package ru.maribobah.academyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Genre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
