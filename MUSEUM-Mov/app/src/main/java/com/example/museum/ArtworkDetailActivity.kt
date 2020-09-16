package com.example.museum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_artwork_detail.*
import kotlinx.android.synthetic.main.activity_detail.*

class ArtworkDetailActivity : AppCompatActivity(), MyOnAuthorsClickedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artwork_detail)

        val listAuthors = listOf<DummyAuthor>(
            DummyAuthor("Autor1"),
            DummyAuthor("Autor2"),
            DummyAuthor("Autor3"),
            DummyAuthor("Autor4"),
            DummyAuthor("Autor5")
        )

        initializeRecyclerView(
            listAuthors,
            this,
            rv_authors
        )

    }

    fun initializeRecyclerView(
        list: List<DummyAuthor>,
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
        rv_authors.layoutManager = GridLayoutManager(activity, 2)
        rv_authors.itemAnimator = DefaultItemAnimator()
        authorsAdapter.notifyDataSetChanged()


    }

    override fun onAuthorClicked(position: Int) {
        val explicitIntent: Intent = Intent(
            this,
            AuthorDetailActivity::class.java
        )
        startActivity(explicitIntent)
    }
}