package com.example.museum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentViewHolder
import kotlinx.android.synthetic.main.activities_types_view_pager.view.*

class PageAdapter(
    private val contextActivity: MainActivity
) : RecyclerView.Adapter<PageAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tituloTextView : TextView
        var activitiesReciclerView: RecyclerView

        init {
            tituloTextView = itemView.findViewById(R.id.tv_títle_activity_type)
            activitiesReciclerView = itemView.findViewById(R.id.rv_activities)
        }
    }


    var listaTitulos: List<String> = listOf(
        "Tours",
        "Films",
        "Performances",
        "Exhibitions"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activities_types_view_pager, parent, false)
        return ViewPagerViewHolder(view)
    }



    override fun getItemCount(): Int {
        return listaTitulos.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val titulo = listaTitulos[position]
        holder.itemView.tv_títle_activity_type.text = titulo
        val activitiesAdapter = ActivitiesRecyclerAdapter(
            titulo,
            contextActivity
        )

        holder.itemView.rv_activities.adapter = activitiesAdapter
        holder.itemView.rv_activities.itemAnimator = DefaultItemAnimator()
        holder.itemView.rv_activities.layoutManager = LinearLayoutManager(contextActivity)
        activitiesAdapter.notifyDataSetChanged()

    }

}