package com.example.museum

import android.content.Intent
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
        var id: Int? = null



        init {
            nameTextView = view.findViewById(R.id.tv_name_activity_preview)
            datesTextView = view.findViewById(R.id.tv_dates_activity_preview)
            pictureImageView = view.findViewById(R.id.iv_activity_preview)
            buyButton = view.findViewById(R.id.btn_buy_activity_preview)
            //buyButton.setOnClickListener{goBuyActivity()}

        }

        fun goBuyActivity(){
            val intent : Intent = Intent(contextActivity,BuyActivity::class.java)
            contextActivity.startActivity(intent)
            if (id != null){
                Log.i("ID", "El id es $id")
            }else{
                Log.i("ID", "No id ")
            }
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
        holder.id = activity.id

        Glide.with(contextActivity)
            .load(activity.imagePath)
            .into(holder.pictureImageView)
        holder.itemView.setOnClickListener{
            clickListener.onActivityClicked(activity, position)
        }

    }

}


interface MyOnActivityListener{
    fun onActivityClicked(activity: Activity, position: Int)
}