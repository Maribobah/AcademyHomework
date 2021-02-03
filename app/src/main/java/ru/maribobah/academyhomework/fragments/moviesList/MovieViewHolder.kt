package ru.maribobah.academyhomework.fragments.moviesList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import ru.maribobah.academyhomework.R
import ru.maribobah.academyhomework.RatingBarSvg
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    private val rate: TextView = itemView.findViewById(R.id.tv_rate)
    private val like: ImageView = itemView.findViewById(R.id.iv_like)
    private val genre: TextView = itemView.findViewById(R.id.tv_genre)
    private val review: TextView = itemView.findViewById(R.id.tv_review)
    private val title: TextView = itemView.findViewById(R.id.tv_title)
    private val duration: TextView = itemView.findViewById(R.id.tv_duration)
    private val rating: RatingBarSvg = itemView.findViewById(R.id.rb_rating)

    fun bindMovie(movie: MovieEntity) {
        rate.text = movie.rate
        genre.text = movie.genres
        title.text = movie.name
        review.text = movie.reviews
        rating.rating = movie.stars

        if (movie.duration.isEmpty()) {
            duration.visibility = View.INVISIBLE
        } else {
            duration.text = movie.duration
        }

        val context = itemView.context
        val circularProgressDrawable = CircularProgressDrawable(context).apply {
            strokeWidth = itemView.resources.getFloat(R.dimen.stroke_width_circular_progress)
            centerRadius = itemView.resources.getFloat(R.dimen.radius_circular_progress)
            setColorSchemeColors(ContextCompat.getColor(context, R.color.activity_color))
            start()
        }

        val radiusCorners = itemView.resources.getFloat(R.dimen.radius_corners_movie_card)

        Glide.with(itemView).load(movie.poster)
            .fitCenter()
            .transform(GranularRoundedCorners(radiusCorners, radiusCorners, 0f, 0f))
            .placeholder(circularProgressDrawable)
            .into(poster)
        setTintColor(like, movie.like)
    }

    private fun setTintColor(imageView: ImageView, value: Boolean) {
        if (value)
            imageView.setColorFilter(imageView.resources.getColor(R.color.activity_color))
        else
            imageView.clearColorFilter()
    }
}