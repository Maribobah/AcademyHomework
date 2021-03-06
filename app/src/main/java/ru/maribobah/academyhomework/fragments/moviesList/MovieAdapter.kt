package ru.maribobah.academyhomework.fragments.moviesList

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.R
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.fragments.categories.FragmentMoviesListClickListener

class MovieAdapter(
    val clickListener: FragmentMoviesListClickListener?
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<MovieEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener {
            clickListener?.onClickMovieCard(movie.id)
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun getItemId(position: Int): Long = movies[position].id

    fun setData(movies: List<MovieEntity>) {
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