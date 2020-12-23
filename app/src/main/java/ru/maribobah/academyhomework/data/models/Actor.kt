package ru.maribobah.academyhomework.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class Actor(
     val id: Int,
     val name: String,
     @SerialName("profile_path")
     val avatar: String
) : Parcelable