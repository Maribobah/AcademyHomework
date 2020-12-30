package ru.maribobah.academyhomework

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import ru.maribobah.academyhomework.data.models.Movie

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    private val rate: TextView = itemView.findViewById(R.id.tv_rate)
    private val like: ImageView = itemView.findViewById(R.id.iv_like)
    private val genre: TextView = itemView.findViewById(R.id.tv_genre)
    private val review: TextView = itemView.findViewById(R.id.tv_review)
    private val title: TextView = itemView.findViewById(R.id.tv_title)
    private val duration: TextView = itemView.findViewById(R.id.tv_duration)
    private val rating: RatingBarSvg = itemView.findViewById(R.id.rb_rating)

    fun bindMovie(movie: Movie) {
        rate.text = movie.rate
        genre.text = MoviePresentation.genresPresentation(movie.genres)
        title.text = movie.name
        review.text = MoviePresentation.reviewsPresentation(movie.reviews)
        duration.text = MoviePresentation.durationPresentation(movie.duration)
        rating.rating = MoviePresentation.starsFormat(movie.stars)

        val context = itemView.context
        val circularProgressDrawable = CircularProgressDrawable(context).also {
            it.strokeWidth = 12f
            it.centerRadius = 48f
            it.setColorSchemeColors(ContextCompat.getColor(context, R.color.activity_color))
            it.start()
        }

        Glide.with(itemView).load(movie.poster)
            .fitCenter()
            .transform(GranularRoundedCorners(32f, 32f, 0f, 0f))
            .placeholder(circularProgressDrawable)
            .into(poster)
        MoviePresentation.setTintColor(like, movie.like)
    }

}