package ru.maribobah.academyhomework.fragments.movieItem

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.maribobah.academyhomework.R
import ru.maribobah.academyhomework.data.localdb.entity.ActorEntity

class MovieCastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_photo)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_avatar)

    private val scaleTransformation: CenterCrop = CenterCrop()
    private val roundedCorners: RoundedCorners = RoundedCorners(
        itemView.resources.getInteger(R.integer.corners_radius_actor_avatar)
    )

    fun bindActor(actor: ActorEntity) {
        if (actor.avatar.isNotEmpty()) {
            Glide.with(itemView).load(actor.avatar)
                .transform(scaleTransformation, roundedCorners)
                .into(avatar)
        }
        name.text = actor.name
    }
}