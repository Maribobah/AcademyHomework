package ru.maribobah.academyhomework

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.data.models.Actor

class MovieCastAdapter(private var actors: List<Actor>? = null) :
    RecyclerView.Adapter<MovieCastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_actor, parent, false)
        return MovieCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        actors?.let {
            holder.bindActor(it[position])
        }
    }

    override fun getItemCount(): Int = actors?.size ?: 0

    fun setData(actors: List<Actor>) {
        this.actors = actors
        notifyDataSetChanged()
    }
}

class MovieCastSpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition > 0) {
            outRect.left = space
        }
    }

}