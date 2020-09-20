package com.example.museum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        if(username.length==0){
            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG)
        }else if(name.length==0){
            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG)
        }else if(email.length==0){
            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG)
        }else if(phone.length==0){
            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG)
        }else{
            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG)
            Log.i("update values","${username}, ${name}, ${email}, ${phone}")
        }

    }
}