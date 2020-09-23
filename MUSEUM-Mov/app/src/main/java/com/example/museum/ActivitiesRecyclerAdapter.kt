package com.example.museum

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.models.Activity
import com.example.museum.ui.login.LoginActivity


class ActivitiesRecyclerAdapter(
    private val activityType: String,
    private val activities: ArrayList<Activity>?,
    private val contextActivity: MainActivity,
    private val clickListener: MyOnActivityListener
): RecyclerView.Adapter<ActivitiesRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){
        var nameTextView: TextView;
        var datesTextView: TextView;
        var pictureImageView: ImageView;
        var buyButton: Button;


        var preferences: SharedPreferences
        var userID: Int = 0

        init {
            preferences = contextActivity.getSharedPreferences(
                EnvironmentVariables.prefsCredentialsName,
                Context.MODE_PRIVATE)

            userID = preferences.getInt("userID", 0)
            nameTextView = view.findViewById(R.id.tv_name_activity_preview)
            datesTextView = view.findViewById(R.id.tv_dates_activity_preview)
            pictureImageView = view.findViewById(R.id.iv_activity_preview)
            buyButton = view.findViewById(R.id.btn_buy_activity_preview)


        }





    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.activity_preview,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if(activities != null) activities.size else 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val activity: Activity = activities!!.get(position)
        val initialDate: String =
            "${activity.dateInitialDate.dayOfMonth} " +
                    "${activity.dateInitialDate.month.toString().take(3)}"
        val finalDate: String =
            "${activity.dateFinalDate.dayOfMonth} " +
                    "${activity.dateFinalDate.month.toString().take(3)}"
        holder.nameTextView.text =  activity.name
        holder.datesTextView.text =
            if(activity.type != "Tour")
            "$initialDate - $finalDate"
            else "Every day"


        Glide.with(contextActivity)
            .load(activity.imagePath)
            .into(holder.pictureImageView)

        holder.buyButton.setOnClickListener { goBuyActivity(holder.userID, activity.id) }

        holder.itemView.setOnClickListener{
            clickListener.onActivityClicked(activity, position)
        }



    }

    fun goBuyActivity(userID: Int, activityID: Int){

        val  intentExplicito : Intent
        if (userID == 0){
            intentExplicito = Intent(contextActivity,LoginActivity::class.java)
        }else {
            intentExplicito = Intent(contextActivity,BuyActivity::class.java)

        }


        intentExplicito.putExtra("activityID", activityID )
        contextActivity.startActivity(intentExplicito)

    }

}


interface MyOnActivityListener{
    fun onActivityClicked(activity: Activity, position: Int)
}