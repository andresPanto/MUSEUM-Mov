package com.example.museum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_detail.*

class ActivityDetailActivity : AppCompatActivity(), MyOnArtworkClickedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        
        
        val listArtwork = arrayListOf<DummyArtwork>(
            DummyArtwork("Artwork1"),
            DummyArtwork("Artwork2"),
            DummyArtwork("Artwork3"),
            DummyArtwork("Artwork4")
        )
        
        initializeRecyclerViewArtwork(
            listArtwork,
            this,
            rv_artworks
        )
        
        
    }
    
    fun initializeRecyclerViewArtwork(
        list: List<DummyArtwork>,
        activity: ActivityDetailActivity,
        recyclerView: RecyclerView
    ){
        val artworkadapter = ArtworksAdapter(
            list,
            activity,
            recyclerView,
            activity
        )
        rv_artworks.adapter = artworkadapter
        rv_artworks.itemAnimator = DefaultItemAnimator()
        rv_artworks.layoutManager = GridLayoutManager(activity, 2)
        artworkadapter.notifyDataSetChanged()

    }

    override fun onArtworkClicked(position: Int) {
        val explicitIntent: Intent = Intent(
            this,
            ArtworkDetailActivity::class.java
        )
        startActivity(explicitIntent)

    }


}