package ru.maribobah.academyhomework.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class Actor(
     @SerialName("id")
     val id: Int,
     @SerialName("name")
     val name: String,
     @SerialName("profile_path")
     val avatar: String
) : Parcelable