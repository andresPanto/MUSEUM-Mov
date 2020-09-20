package com.example.museum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.museum.httpHandlers.AuthorHTTPHandler
import com.example.museum.models.Artwork
import com.example.museum.models.ArtworkAuthor
import com.example.museum.models.Author
import kotlinx.android.synthetic.main.activity_artwork_detail.*
import kotlinx.android.synthetic.main.activity_author_detail.*

class AuthorDetailActivity : AppCompatActivity() {
    val authorHTTPHandler: AuthorHTTPHandler = AuthorHTTPHandler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_detail)

        val idAuthor: Int = intent.getIntExtra("id", 0)

        if (idAuthor == 0){
            Log.i("Error intent author detail", "ID = 0")
            finish()
        }

        val author: Author? =  authorHTTPHandler.getOne(idAuthor)



        if (author == null) {
            Log.i("Error author activity", "No hay author")
            finish()
        }

        Log.i("Artwork", "${author?.fullName}")

        initializeAuthor(author!!)







    }

    fun initializeAuthor(author: Author){
        Glide.with(this)
            .load(author.imagePath)
            .into(iv_image_author_detail)

        tv_author_country.text = author.country
        tv_author_name_detail.text = author.fullName
        tv_description_author_detail.text = author.description
    }
}