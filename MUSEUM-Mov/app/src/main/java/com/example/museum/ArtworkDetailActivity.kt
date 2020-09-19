package com.example.museum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.museum.httpHandlers.ArtworkAuthorHTTPHandler
import com.example.museum.httpHandlers.ArtworkHTTPHandler
import com.example.museum.models.*
import kotlinx.android.synthetic.main.activity_artwork_detail.*
import kotlinx.android.synthetic.main.activity_author_detail.*
import kotlinx.android.synthetic.main.activity_detail.*

class ArtworkDetailActivity : AppCompatActivity(), MyOnAuthorsClickedListener {
    val artworkHTTPHandler: ArtworkHTTPHandler = ArtworkHTTPHandler()
    val artworkAuthorHTTPHandler: ArtworkAuthorHTTPHandler = ArtworkAuthorHTTPHandler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artwork_detail)


        val idArtwork: Int = intent.getIntExtra("id", 0)

        if (idArtwork == 0){
            Log.i("Error intent artwork detail", "ID = 0")
            finish()
        }

        val artwork: Artwork? =  artworkHTTPHandler.getOne(idArtwork)

        val artworkAuthors: ArrayList<ArtworkAuthor> = artworkAuthorHTTPHandler.getAllByArtwork(idArtwork)

        if (artwork == null ) {
            Log.i("Error intent artwork detail", "No hay artwork")
            finish()
        }

        Log.i("Artwork", "${artwork?.name}")

        initializeArtwork(artwork!!)


        initializeRecyclerView(
            artworkAuthors,
            this,
            rv_authors
        )

    }

    fun initializeArtwork(artwork: Artwork){
        tv_artwork_name.text = artwork.name
        tv_artwork_type_detail.text = artwork.type
        tv_year_artwork.text = artwork.year.toString()
        tv_artwork_description.text = artwork.description
        Glide
            .with(this)
            .load(artwork.imagePath)
            .into(iv_artwork_detail_image)
    }


    fun initializeRecyclerView(
        list: ArrayList<ArtworkAuthor>,
        activity: ArtworkDetailActivity,
        recyclerView: RecyclerView
    ){
        val authorsAdapter = AuthorsAdapter(
            list,
            activity,
            recyclerView,
            activity
        )
        rv_authors.adapter = authorsAdapter
        rv_authors.layoutManager = LinearLayoutManager(this)
        rv_authors.itemAnimator = DefaultItemAnimator()
        authorsAdapter.notifyDataSetChanged()


    }

    override fun onAuthorClicked(author: Author, position: Int) {
        val explicitIntent: Intent = Intent(
            this,
            AuthorDetailActivity::class.java
        )
        explicitIntent.putExtra("id", author.id)
        startActivity(explicitIntent)
    }
}