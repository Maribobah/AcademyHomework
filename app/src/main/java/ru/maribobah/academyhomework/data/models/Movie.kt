package ru.maribobah.academyhomework.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movie(
    val id: Int,
    val name: String,
    val rate: String,
    val like: Boolean = false,
    val stars: Float,
    val reviews: Int,
    val duration: Int,
    val poster: String,
    val backdrop: String,
    val storyline: String,
    val actors: List<Actor>,
    val genres: List<Genre>
) : Parcelable
