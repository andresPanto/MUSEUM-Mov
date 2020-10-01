package com.example.museum

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.httpHandlers.ActivityHTTPHandler
import com.example.museum.httpHandlers.PurchaseHTTPHandler
import com.example.museum.httpHandlers.ScheduleHTTPHandler
import com.example.museum.models.Activity
import com.example.museum.models.Purchase
import com.example.museum.models.Schedule
import com.example.museum.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_buy.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class BuyActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var languages = arrayOf("morning", "late", "night")
    val NEW_SPINNER_ID = 1


    val activityHandler: ActivityHTTPHandler = ActivityHTTPHandler()
    val scheduleHandler: ScheduleHTTPHandler = ScheduleHTTPHandler()
    val purchaseHandler: PurchaseHTTPHandler = PurchaseHTTPHandler()
    lateinit var preferences: SharedPreferences
    var userID: Int = 0
    var idActivity: Int = 0
    var activity: Activity? = null
    lateinit var adapterSchedule: ArrayAdapter<Schedule>
    val dateFormat = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        preferences = getSharedPreferences(
            EnvironmentVariables.prefsCredentialsName,
            Context.MODE_PRIVATE
        )
        userID = preferences.getInt("userID", 0)


        idActivity = intent.getIntExtra("activityID", 0)

        if (userID == 0) {
            val intentExplicito = Intent(this, LoginActivity::class.java)
            finish()
        }
        if (idActivity == 0) {
            Toast.makeText(this, "Cannot load Activity", Toast.LENGTH_LONG)
            finish()
        }

        activity = activityHandler.getOne(idActivity)
        if (activity != null) {
            Log.i("Nombre Activity", "${activity?.name}")
            val schedules: ArrayList<Schedule> = scheduleHandler.getAllByActivity(idActivity)
            setInitialValues(activity!!, schedules)
        }


        btn_buy_purchase.setOnClickListener {
            makePurchase()
        }




    }

    fun makePurchase(){
        val attendanceDate: String = tv_selected_date_purchase.text.toString()
        if(attendanceDate == "Date Not Selected"){
            Toast.makeText(this, "Choose a date", Toast.LENGTH_LONG)
            Log.i("Calendario", "Fecha no seleccionada")
            return
        }

        val purchaseTime : String = dateFormat.format(Date())
        val quantity: Int = np_purchase.value
        val total: String = tv_performance_total.text.toString().removePrefix("$")
        val user: Int = userID
        val selectedSchedule: Schedule =sp_schedules.selectedItem as Schedule
        val schedule: Int = selectedSchedule.id
        val params: List<Pair<String,String>> = listOf(
            "attendanceDate" to "$attendanceDate",
            "purchaseTime" to "$purchaseTime",
            "quantity" to "$quantity",
            "total" to "$total",
            "user" to "$user",
            "schedule" to "$schedule"
        )
        Log.i("attendanceDate", "$attendanceDate")
        Log.i("purchaseTime", "$purchaseTime")
        Log.i("quantity", "$quantity")
        Log.i("total", "$total")
        Log.i("user", "$user")
        Log.i("schedule", "$schedule")
        val purchase: Purchase? = purchaseHandler.createOne(params)
        if (purchase != null){
            val newQuantity = selectedSchedule.capacity - quantity;
            val paramsSchedule: List<Pair<String,String>> = listOf(
                "capacity" to "$newQuantity"
            )
            val updatedSchedule: Schedule? = scheduleHandler.updateOne(paramsSchedule, schedule)
            if (updatedSchedule != null){
                val intent: Intent = Intent(
                    this,
                    UserAccount::class.java
                )
                startActivity(intent)
                finish()
            }else{
                Log.i("schedule", "is null")
                Toast.makeText(this, "Failed to purchase", Toast.LENGTH_LONG)
            }
        }else{
            Log.i("purchase", "is null")
            Toast.makeText(this, "Failed to purchase", Toast.LENGTH_LONG)
        }

    }

    fun setInitialValues(activity: Activity, schedules: ArrayList<Schedule>) {
        Glide.with(this)
            .load(activity.imagePath)
            .into(iv_activity_buy_activity)

        tv_activity_name_purchase.setText(activity.name)
        tv_activity_price_purchase.setText(activity.price.toString())
        tv_activity_duration_purchase.setText(activity.duration)
        tv_selected_date_purchase.setText("Date Not Selected")
        tv_performance_total.setText("${activity.price}")


        adapterSchedule = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            schedules
        )

        sp_schedules.adapter = adapterSchedule
        sp_schedules.setSelection(0, false)
        sp_schedules.prompt = "Select your Schedule"
        np_purchase.minValue = 1
        np_purchase.value = 1
        if (schedules.size > 0){
            np_purchase.maxValue = schedules[0].capacity
        }else{
            np_purchase.maxValue = 10
        }

        np_purchase.setOnValueChangedListener { picker, oldVal, newVal ->
            val price = activity.price
            val total = newVal * price
            tv_performance_total.setText("$$total")

        }
        sp_schedules.onItemSelectedListener = this
        btn_calendar.setOnClickListener {
            getValuesDataCalendar(activity)
        }


    }


    fun getValuesDataCalendar(activity: Activity) {
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
//                tv_selected_date_purchase.setText("" + dayOfMonth + " " + (monthOfYear + 1) + ", " + year)
                tv_selected_date_purchase.setText("${year}-${(monthOfYear + 1)}-${dayOfMonth}")
            },
            year,
            month,
            day
        )

//        dpd.datePicker.minDate =  activity.dateInitialDate.toEpochDay()
//        dpd.datePicker.maxDate = activity.dateFinalDate.toEpochDay()
        dpd.show()
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {
        p0?.setSelection(0)
    }

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val schedule: Schedule = p0?.getItemAtPosition(position) as Schedule
        Log.i("Schedule", "${schedule.schedule}, ${schedule.capacity}, ${schedule.status}")
        np_purchase.maxValue = schedule.capacity
        (p0.getChildAt(0)as TextView).setTextSize(16.0f)

    }
}




