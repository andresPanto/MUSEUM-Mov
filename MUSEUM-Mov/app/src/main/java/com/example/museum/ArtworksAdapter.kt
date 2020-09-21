package com.example.museum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.museum.models.ActivityArtwork
import com.example.museum.models.Artwork

class ArtworksAdapter(
    private val listActivityArtwork: ArrayList<ActivityArtwork>,
    private val context: ActivityDetailActivity,
    private val recyclerView: RecyclerView,
    private val clickListener: MyOnArtworkClickedListener
): RecyclerView.Adapter<ArtworksAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){

        var artworkImageView: ImageView;
        var artworkTitleTextView: TextView;
        init {
            artworkImageView = view.findViewById(R.id.iv_artwork_preview)
            artworkTitleTextView = view.findViewById(R.id.tv_title_artwork_preview)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.artwork_preview,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listActivityArtwork.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val artwork : Artwork = listActivityArtwork[position].artwork as Artwork
        Glide
            .with(context)
            .load(artwork.imagePath)
            .into(holder.artworkImageView)
        holder.artworkTitleTextView.text = artwork.name
        holder.itemView.setOnClickListener{
            clickListener.onArtworkClicked(artwork, position)
        }
    }

}

interface MyOnArtworkClickedListener{
    fun onArtworkClicked(artwork: Artwork, position: Int)
}