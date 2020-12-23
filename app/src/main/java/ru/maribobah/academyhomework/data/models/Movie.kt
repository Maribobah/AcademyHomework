package ru.maribobah.academyhomework.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import android.widget.ImageView
import ru.maribobah.academyhomework.R

@Parcelize
class Movie(
    val name: String,
    val rate: String,
    val like: Boolean,
    val genres: String,
    val stars: Int,
    val reviews: Int,
    val duration: Int,
    val poster: Int,
    val posterDetails: Int,
    val storyline: String,
    val actors: List<Actor>
) : Parcelable