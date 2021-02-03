package ru.maribobah.academyhomework.fragments.movieItem

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.R
import ru.maribobah.academyhomework.data.localdb.entity.ActorEntity

class MovieCastAdapter :
    RecyclerView.Adapter<MovieCastViewHolder>() {

    private var actors: List<ActorEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_actor, parent, false)
        return MovieCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        holder.bindActor(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    override fun getItemId(position: Int): Long = actors[position].id

    fun setData(actors: List<ActorEntity>) {
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