package com.example.museum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArtworksAdapter(
    private val listArtworks: List<DummyArtwork>,
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
        return listArtworks.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val artwork : DummyArtwork = listArtworks[position]
        holder.artworkImageView.setImageResource(R.drawable.artwork1)
        holder.artworkTitleTextView.text = artwork.title
        holder.itemView.setOnClickListener{
            clickListener.onArtworkClicked(position)
        }
    }

}

interface MyOnArtworkClickedListener{
    fun onArtworkClicked(position: Int)
}