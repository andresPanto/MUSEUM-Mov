package com.example.museum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.museum.models.ArtworkAuthor
import com.example.museum.models.Author
import java.security.AccessControlContext

class AuthorsAdapter(
    private val listArtworkAuthor: ArrayList<ArtworkAuthor>,
    private val context: ArtworkDetailActivity,
    private val recyclerView: RecyclerView,
    private val clickListener: MyOnAuthorsClickedListener
): RecyclerView.Adapter<AuthorsAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){
        var authorImageView: ImageView;
        var authorTitleTextView: TextView;
        init {
            authorImageView = view.findViewById(R.id.iv_author_preview)
            authorTitleTextView = view.findViewById(R.id.tv_author_name_preview)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.author_preview,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listArtworkAuthor.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val author : Author = listArtworkAuthor[position].author as Author
        //holder.authorImageView.setImageResource(R.drawable.author)
        Glide
            .with(context)
            .load(author.imagePath)
            .into(holder.authorImageView)

        holder.authorTitleTextView.text = author.fullName
        holder.itemView.setOnClickListener{
            clickListener.onAuthorClicked(author, position)
        }
    }
}

interface MyOnAuthorsClickedListener{
    fun onAuthorClicked(author: Author, position: Int)
}