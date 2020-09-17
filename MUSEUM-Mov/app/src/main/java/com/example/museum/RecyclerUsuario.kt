package com.example.museum

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerUsuario (
    private val listPurchase : List<Purchase>,
    private val contextoPurchase : UserAccount,
    private val recyclerView: RecyclerView
):androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerUsuario.ViewPagerViewPurchase>(){
    inner class ViewPagerViewPurchase(view: View):
            androidx.recyclerview.widget.RecyclerView.ViewHolder(view){

        var algo1 : TextView
        var algo2 : TextView
        var accionButton:Button
        init {
            algo1= view.findViewById(R.id.txt_purchase_name)
            algo2=view.findViewById(R.id.txt_purchase_quantity)
            accionButton=view.findViewById(R.id.btn_edit_purchase)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): RecyclerUsuario.ViewPagerViewPurchase {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.purchase_adapter,
                parent,
                false
            )
        return ViewPagerViewPurchase(itemView)
    }

    override fun getItemCount(): Int {
        return listPurchase.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewPurchase, position: Int) {
        var purchase = listPurchase[position]
        holder.algo1.text = purchase.name
        holder.algo2.text = purchase.name
        holder.accionButton.text = "Like ${purchase.name}"
    }

}

class Purchase(var name:String){

}