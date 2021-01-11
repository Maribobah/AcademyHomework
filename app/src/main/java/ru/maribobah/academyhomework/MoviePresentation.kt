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

        fun reviewsPresentation(reviews: Int): String = "$reviews reviews"

        fun durationPresentation(duration: Int): String = "$duration MIN"

        fun genresPresentation(genres: List<Genre>): String = genres.joinToString { it.name }

        fun starsFormat(stars: Float): Float = stars / 2

        fun ratePresentation(adult: Boolean): String = if (adult) "16+" else "13+"

    }

}