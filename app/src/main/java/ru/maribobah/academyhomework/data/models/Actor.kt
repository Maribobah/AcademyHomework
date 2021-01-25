package ru.maribobah.academyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Actor(
     @SerialName("id")
     val id: Int,
     @SerialName("name")
     val name: String,
     @SerialName("profile_path")
     var avatar: String?
)