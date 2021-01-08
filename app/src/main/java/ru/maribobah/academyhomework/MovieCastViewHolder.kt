package ru.maribobah.academyhomework

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.maribobah.academyhomework.data.models.Actor

class MovieCastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_photo)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_avatar)

    fun bindActor(actor: Actor) {
        Glide.with(itemView).load(actor.avatar).centerCrop().into(avatar)
        name.text = actor.name
    }

}