package ru.maribobah.academyhomework.data.tmdb

import android.widget.ImageView
import ru.maribobah.academyhomework.R
import ru.maribobah.academyhomework.data.models.Genre

class MovieConverter {

    companion object {
        fun setTintColor(imageView: ImageView, value: Boolean) {
            if (value)
                imageView.setColorFilter(imageView.resources.getColor(R.color.activity_color))
            else
                imageView.clearColorFilter()
        }

        fun fromVoteCountToReviews(voteCount: Int): String = "$voteCount reviews"

        fun fromRuntimeToDuration(runtime: Int): String {
            return  if (runtime == 0) "" else "$runtime MIN"
        }

        fun genresPresentation(genres: List<Genre>): String = genres.joinToString { it.name }

        fun starsFormat(stars: Float): Float = stars / 2

        fun fromAdultToRate(adult: Boolean): String = if (adult) "16+" else "13+"

    }

}