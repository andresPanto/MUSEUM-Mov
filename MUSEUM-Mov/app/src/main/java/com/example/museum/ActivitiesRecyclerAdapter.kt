package com.example.museum

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

class ActivitiesRecyclerAdapter(
    private val activityType: String,
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
            buyButton.setOnClickListener{goBuyActivity()}

        }

        fun goBuyActivity(){
            if (id != null){
                Log.i("ID", "El id es $id")
            }else{
                Log.i("ID", "No id ")
            }
        }



    }

    var listActivities : ArrayList<DummyActivity>
    init {
        listActivities = DummyDatabase.activities
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
        return listActivities.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val activity: DummyActivity = listActivities.get(position)
        holder.nameTextView.text =  activity.name
        holder.datesTextView.text =  "${activity.initialDate} - ${activity.finalDate}"
        holder.id = activity.id

        holder.pictureImageView.setImageResource(R.drawable.ejemplo)
        holder.itemView.setOnClickListener{
            clickListener.onActivityClicked(activity, position)
        }

    }

}


interface MyOnActivityListener{
    fun onActivityClicked(activity: DummyActivity, position: Int)
}