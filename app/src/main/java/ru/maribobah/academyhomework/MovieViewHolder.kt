package ru.maribobah.academyhomework

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

        Glide.with(itemView).load(movie.poster).fitCenter().into(poster)
        MoviePresentation.setTintColor(like, movie.like)
    }

}