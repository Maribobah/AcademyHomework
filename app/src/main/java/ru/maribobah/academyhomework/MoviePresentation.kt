package ru.maribobah.academyhomework

import android.widget.ImageView
import ru.maribobah.academyhomework.data.models.Genre

class MoviePresentation {

    companion object {
        fun setTintColor(imageView: ImageView, value: Boolean) {
            if (value)
                imageView.setColorFilter(imageView.resources.getColor(R.color.activity_color))
            else
                imageView.clearColorFilter()
        }
        fun reviewsPresentation(reviews: Int) : String {
            return "$reviews reviews"
        }
        fun durationPresentation(duration: Int) : String {
            return "$duration MIN"
        }
        fun genresPresentation(genres: List<Genre>) : String {
            return genres.joinToString { it.name }
        }
        fun starsFormat(stars: Float) : Float {
            return stars/2
        }
    }

}