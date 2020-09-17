package com.example.museum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_update_account.*

class UpdateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_account)

        btn_save.setOnClickListener{
            getValues()
        }
    }

    private fun getValues(){
        var username =txt_username_update.toString()
        var name = txt_name_update.toString()
        var email = txt_email_update.toString()
        var phone = txt_phone_update.toString()
        Log.i("update values","${username}, ${name}, ${email}, ${phone}")
    }
}