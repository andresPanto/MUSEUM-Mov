package com.example.museum

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.museum.httpHandlers.*
import com.example.museum.models.*
import com.example.museum.ui.login.LoginActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyOnActivityListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)








        vp2_principal.adapter = PageAdapter(this)


        TabLayoutMediator(tabL_principal, vp2_principal) {
            tab, position ->
            when(position){
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

    fun goLogIn(){
        val intent : Intent = Intent(this, LoginActivity::class.java)
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

}