package ru.maribobah.academyhomework

import android.widget.ImageView

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
    }

}