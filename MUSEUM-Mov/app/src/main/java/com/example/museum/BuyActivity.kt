package com.example.museum

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_buy.*
import java.util.*

class BuyActivity : AppCompatActivity() {

    var languages = arrayOf("morning", "late", "night")
    val NEW_SPINNER_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        btn_calendar.setOnClickListener{
            getValuesDataCalendar()
        }


        txt_performance_price.text=""
        txt_performance_duration.text=""
        txt_performance_name.text=""


        number_picker.minValue = 1
        number_picker.maxValue = 10

        number_picker.wrapSelectorWheel = false

        number_picker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            text_view.text = "Selected Value : $newVal"
        }


        var aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spinner)
        {
            adapter = aa
            setSelection(0, false)
//            onItemSelectedListener = this
            prompt = "Select your favourite schedule"
            gravity = Gravity.CENTER

        }
        val spinner = Spinner(this)
        spinner.id = NEW_SPINNER_ID


    }
    fun getValuesDataCalendar(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in TextView
            txt_calendar.setText("" + dayOfMonth + " " +(monthOfYear+1) + ", " + year)
        }, year, month, day)
        dpd.datePicker.minDate
        dpd.datePicker.maxDate
        dpd.show()
    }
    fun calculateTotal(){

        txt_performance_price.text=""
    }
}
