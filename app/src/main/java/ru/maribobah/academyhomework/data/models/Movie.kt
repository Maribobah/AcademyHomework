package ru.maribobah.academyhomework.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
class Movie(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("rate")
    val rate: String,
    @SerialName("like")
    val like: Boolean = false,
    @SerialName("stars")
    val stars: Float,
    @SerialName("reviews")
    val reviews: Int,
    @SerialName("duration")
    val duration: Int,
    @SerialName("poster")
    val poster: String,
    @SerialName("backdrop")
    val backdrop: String,
    @SerialName("storyline")
    val storyline: String,
    @SerialName("actors")
    val actors: List<Actor>,
    @SerialName("genres")
    val genres: List<Genre>
) : Parcelable
