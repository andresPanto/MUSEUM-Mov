package com.example.museum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.museum.httpHandlers.ScheduleHTTPHandler
import com.example.museum.models.Activity
import com.example.museum.models.Purchase
import com.example.museum.models.Schedule

class RecyclerUsuario (
    private val listPurchase : ArrayList<Purchase>,
    private val context : UserAccount
):RecyclerView.Adapter<RecyclerUsuario.ViewPagerViewPurchase>(){
    inner class ViewPagerViewPurchase(view: View):
            RecyclerView.ViewHolder(view){

        var activityNameTextView : TextView
        var scheduleTextView : TextView
        var attendanceDateTextView: TextView
        var totalTextView: TextView
        var quantityTextView: TextView
        var purchaseTimeTextView: TextView

        init {
            activityNameTextView = view.findViewById(R.id.tv_purchase_name)
            scheduleTextView = view.findViewById(R.id.tv_purchase_schedule)
            attendanceDateTextView = view.findViewById(R.id.tv_attendance_date_purchase)
            totalTextView = view.findViewById(R.id.tv_purchase_total)
            quantityTextView = view.findViewById(R.id.tv_quantity_purchase)
            purchaseTimeTextView = view.findViewById(R.id.tv_purchase_time)


        }
    }
    val scheduleHandler : ScheduleHTTPHandler = ScheduleHTTPHandler()

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
        var purchase: Purchase = listPurchase[position]
        val scheduleID: Int = purchase.schedule as Int
        var schedule: Schedule? = scheduleHandler.getOne(scheduleID) // es int no Schedule???
        if (schedule != null){
            var activity: Activity = schedule.activity as Activity
            holder.scheduleTextView.text = schedule.schedule
            holder.activityNameTextView.text = activity.name
        }


        holder.attendanceDateTextView.text = purchase.dateAttendanceDate.toString()
        holder.purchaseTimeTextView.text = purchase.timePurchaseTime.toString()
        holder.totalTextView.text = purchase.total.toString()
        holder.quantityTextView.text = purchase.quantity.toString()


    }

}

