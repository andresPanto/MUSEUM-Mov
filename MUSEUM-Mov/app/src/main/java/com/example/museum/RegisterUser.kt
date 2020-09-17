package com.example.museum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.museum.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        btn_sing_me_up.setOnClickListener{
            getValues()
        }
        btn_log_in_r.setOnClickListener{
            irLoginActivity()
        }
    }
    private fun irLoginActivity(){
        var intentExplicito = Intent(this, LoginActivity::class.java)
        this.startActivity(intentExplicito)
    }

    private fun getValues(){
        var full_name : String = txt_user_full_name.toString()
        var email_user: String = txt_email.toString()
        var phone_number:String =txt_phone_number.toString()
        var username =txt_uesrname_reg.toString()
        var password = txt_password.toString()
        var password_confirmation=txt_password_conf.toString()
        Log.i("Valores Registro","${full_name}, ${email_user}, ${phone_number}, ${username}, ${password}, ${password_confirmation}")
    }
}