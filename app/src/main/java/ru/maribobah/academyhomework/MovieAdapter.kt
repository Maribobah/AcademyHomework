package ru.maribobah.academyhomework

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.data.models.Movie

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
        holder.bindMovie(movies[position])
        holder.itemView.setOnClickListener {
            clickListener?.onClickMovieCard(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun getItemId(position: Int): Long = position.toLong()

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