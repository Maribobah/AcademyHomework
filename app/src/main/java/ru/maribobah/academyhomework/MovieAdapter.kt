package ru.maribobah.academyhomework

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.maribobah.academyhomework.data.models.Movie
import java.security.AccessController

class MovieAdapter(
    private val movies: List<Movie>,
    val clickListener: FragmentMoviesListClickListener?
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener?.onClickMovieCard(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun getItemId(position: Int): Long = position.toLong()

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    private val rate: TextView = itemView.findViewById(R.id.tv_rate)
    private val like: ImageView = itemView.findViewById(R.id.iv_like)
    private val genre: TextView = itemView.findViewById(R.id.tv_genre)
    private val review: TextView = itemView.findViewById(R.id.tv_review)
    private val title: TextView = itemView.findViewById(R.id.tv_title)
    private val duration: TextView = itemView.findViewById(R.id.tv_duration)
    private val rating: RatingBarSvg = itemView.findViewById(R.id.rb_rating)

    fun onBind(movie: Movie) {
        rate.text = movie.rate
        genre.text = movie.genres.joinToString { it.name }
        title.text = movie.name
        review.text = MoviePresentation.reviewsPresentation(movie.reviews)
        duration.text = MoviePresentation.durationPresentation(movie.duration)
        rating.progress = movie.stars

        Glide.with(itemView).load(movie.poster).fitCenter().into(poster)
        MoviePresentation.setTintColor(like, movie.like)
    }
}

class MovieSpaceItemDecoration(private val padding: Int, private val gridSize: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        outRect.left = padding
        outRect.right = if ((itemPosition + 1) % gridSize == 0) {
            padding
        } else 0
        outRect.top = if (itemPosition < gridSize) {
            0
        } else padding
        outRect.bottom = 0
    }
}