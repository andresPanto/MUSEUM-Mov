package com.example.museum

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.httpHandlers.ActivityArtworkHTTPHandler
import com.example.museum.httpHandlers.ActivityHTTPHandler
import com.example.museum.models.Activity
import com.example.museum.models.ActivityArtwork
import com.example.museum.models.Artwork
import com.example.museum.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_detail.*

class ActivityDetailActivity : AppCompatActivity(), MyOnArtworkClickedListener {
    val activityHandler: ActivityHTTPHandler = ActivityHTTPHandler()
    val activityArtworkHandler: ActivityArtworkHTTPHandler = ActivityArtworkHTTPHandler()

    lateinit var preferences: SharedPreferences
    var userID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        preferences = getSharedPreferences(
            EnvironmentVariables.prefsCredentialsName,
            Context.MODE_PRIVATE)
        userID = preferences.getInt("userID", 0)

        val id: Int = intent.getIntExtra("id", 0)
        if (id == 0){
            Log.i("Error intent activity detail", "ID = 0")
            finish()
        }

        val activity: Activity? =  activityHandler.getOne(id)

        val activityArtworks: ArrayList<ActivityArtwork> = activityArtworkHandler.getAllByActivity(id)

        if (activity == null){
            Log.i("Error intent activity detail", "No hay activity")
            finish()
        }

        initializeActivity(activity!!)

        initializeRecyclerViewArtwork(
            activityArtworks,
            this,
            rv_artworks
        )
        
        
    }

    fun initializeActivity(activity: Activity){
        Glide
            .with(this)
            .load(activity.imagePath)
            .into(iv_activity_detail_image)
        tv_activity_name_activity.text = activity.name
        tv_activity_type_activity.text = activity.type
        tv_initial_date.text = "${activity.dateInitialDate.dayOfMonth} - " +
                "${activity.dateInitialDate.month.toString().take(3)}, " +
                "${activity.dateInitialDate.year}"
        tv_final_date.text = "${activity.dateFinalDate.dayOfMonth} - " +
                "${activity.dateFinalDate.month.toString().take(3)}, " +
                "${activity.dateFinalDate.year}"
        tv_location.text = activity.location
        tv_duration.text = activity.duration
        tv_pm_phone.text = activity.pmPhoneNumber
        tv_pm_name.text = activity.pmName
        btn_buy_activity.text = "Buy it for: $${activity.price}"
        btn_buy_activity.setOnClickListener { goBuyActivity(activity.id) }
    }

    fun goBuyActivity(activityID: Int){
        val  intentExplicito : Intent
        if (userID == 0){
            intentExplicito = Intent(this, LoginActivity::class.java)
        }else {
            intentExplicito = Intent(this,BuyActivity::class.java)

        }

        intentExplicito.putExtra("activityID", activityID )
        this.startActivity(intentExplicito)
        finish()
    }
    
    fun initializeRecyclerViewArtwork(
        list: ArrayList<ActivityArtwork>,
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

    override fun onArtworkClicked(artwork: Artwork, position: Int) {
        val explicitIntent: Intent = Intent(
            this,
            ArtworkDetailActivity::class.java
        )
        Log.i("Artwork ID", "${artwork.id}")
        explicitIntent.putExtra("id", artwork.id)
        startActivity(explicitIntent)

    }


}