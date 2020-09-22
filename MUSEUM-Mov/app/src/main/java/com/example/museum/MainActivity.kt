package com.example.museum

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.httpHandlers.*
import com.example.museum.models.*
import com.example.museum.models.Purchase
import com.example.museum.ui.login.LoginActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyOnActivityListener {


    private var userID : Int = 0
    private lateinit var preferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         preferences = getSharedPreferences(
             EnvironmentVariables.prefsCredentialsName,
             Context.MODE_PRIVATE)
         userID = preferences.getInt("userID", 0)

        if (userID != 0) {
            val user: User? = UserHTTPHandler().getOne(userID)
            if (user != null) {
                Glide.with(this)
                    .load(user.imagePath)
                    .into(iv_acount_image)
            }else{
                Log.i("User", "No user in API")
            }
        }else{
            Log.i("User", "No user credentials")
        }






        vp2_principal.adapter = PageAdapter(this)


        TabLayoutMediator(tabL_principal, vp2_principal) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tours";
                    tab.setIcon(R.drawable.ic_baseline_tour)
                }
                1 -> {
                    tab.text = "Films"
                    tab.setIcon(R.drawable.ic_baseline_film)
                }
                2 -> {
                    tab.text = "Performances"
                    tab.setIcon(R.drawable.ic_baseline_performance)
                }
                3 -> {
                    tab.text = "Exhibitions"
                    tab.setIcon(R.drawable.ic_baseline_exhibition)
                }
            }
        }.attach()
        iv_acount_image.setOnClickListener { goLogIn() }
    }

    fun goLogIn() {
        val intent: Intent
        if (userID == 0){
            intent =  Intent(this, LoginActivity::class.java)

        }else{
            intent =  Intent(this, UserAccount::class.java)
        }


        startActivity(intent)

    }


    override fun onActivityClicked(activity: Activity, position: Int) {
        Log.i("OnClick", "Pos: $position, Name: ${activity.name}")
        val intent: Intent = Intent(
            this,
            ActivityDetailActivity::class.java
        )
        intent.putExtra("id", activity.id)
        startActivity(intent)


    }

    override fun onResume() {
        super.onResume()
        if (userID != 0) {
            val user: User? = UserHTTPHandler().getOne(userID)
            if (user != null) {
                Glide.with(this)
                    .load(user.imagePath)
                    .into(iv_acount_image)
            }else{
                Log.i("User", "No user in API")
            }
        }else{
            Log.i("User", "No user credentials")
        }
    }

}