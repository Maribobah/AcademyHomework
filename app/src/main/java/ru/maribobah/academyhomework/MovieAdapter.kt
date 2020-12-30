package ru.maribobah.academyhomework

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.data.models.Movie

class MovieAdapter(
    private var movies: List<Movie>? = null,
    val clickListener: FragmentMoviesListClickListener?
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies?.let {
            val movie = it[position]
            holder.bindMovie(movie)
            holder.itemView.setOnClickListener {
                clickListener?.onClickMovieCard(movie)
            }
        }
    }

    override fun getItemCount(): Int = movies?.size ?: 0

    override fun getItemId(position: Int): Long {
        return movies?.let { it[position].id.toLong() } ?: RecyclerView.NO_ID
    }

    fun setData(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
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