package com.example.museum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext

class AuthorsAdapter(
    private val listAuthors: List<DummyAuthor>,
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
        return listAuthors.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val author : DummyAuthor = listAuthors[position]
        holder.authorImageView.setImageResource(R.drawable.author)
        holder.authorTitleTextView.text = author.name
        holder.itemView.setOnClickListener{
            clickListener.onAuthorClicked(position)
        }
    }
}

interface MyOnAuthorsClickedListener{
    fun onAuthorClicked(position: Int)
}