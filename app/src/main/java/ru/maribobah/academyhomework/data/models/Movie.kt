package ru.maribobah.academyhomework.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import android.widget.ImageView
import ru.maribobah.academyhomework.R

@Parcelize
class Movie (
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
) : Parcelable {
    val reviewsStr: String
        get() = "$reviews reviews"

    fun setTintColorForStar(imageView: ImageView, number: Int) {
        setTintColor(imageView, number <= stars)
    }

    companion object {
        fun setTintColor(imageView: ImageView, value: Boolean) {
            if (value)
                imageView.setColorFilter(imageView.resources.getColor(R.color.activity_color))
            else
                imageView.clearColorFilter()
        }
    }
}