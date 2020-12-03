package ru.maribobah.academyhomework

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.data.models.Actor

class CastMovieAdapter(val actors: List<Actor>) : RecyclerView.Adapter<CastMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_actor, parent, false)
        return  CastMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastMovieViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    override fun getItemId(position: Int): Long = position.toLong()
}

class CastMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_photo)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_avatar)

    fun onBind(actor: Actor) {
        avatar.setImageResource(actor.avatar)
        name.text = actor.name
    }

}

class CastMovieSpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition > 0) outRect.left = space

    }
}