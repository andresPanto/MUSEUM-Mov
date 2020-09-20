package com.example.museum

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.httpHandlers.UserHTTPHandler
import com.example.museum.models.User
import com.example.museum.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_account.*

class UserAccount : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    private var userID: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)

        preferences = getSharedPreferences(
            EnvironmentVariables.prefsCredentialsName,
            Context.MODE_PRIVATE)
        userID = preferences.getInt("userID", 0)

        if (userID != 0) {
            val user: User? = UserHTTPHandler().getOne(userID)
            if (user != null) {
                Glide.with(this)
                    .load(user.imagePath)
                    .into(img_user_logo)
            }else{
                Log.i("User", "No user in API")
            }
        }else{
            Log.i("User", "No user credentials")
        }

        var listPurchase = arrayListOf<Purchase>()
        listPurchase.add(
            Purchase("nombre 1")
        )

        listPurchase.add(
            Purchase("nombre 2")
        )

        val boton_update = findViewById<Button>(R.id.btn_update_account)
        val boton_out = findViewById<Button>(R.id.btn_log_out)

        boton_update.setOnClickListener{
            irUpdateAccount()
        }
        boton_out.setOnClickListener{
            irLoginActivity()
        }
        img_user_logo.setOnClickListener{
            setValues("","","","")
        }

        iniciarRv(listPurchase,this,rv_purchase)

    }
    fun iniciarRv(
        list: List<Purchase>,
        activity: UserAccount,
        recicler_view: RecyclerView
    ){
        val adaptadorPurchase = RecyclerUsuario(list,activity,recicler_view)
        rv_purchase.adapter = adaptadorPurchase
        rv_purchase.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_purchase.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptadorPurchase.notifyDataSetChanged()
    }

    private fun setValues(username:String,name:String,email:String,phone:String){
        txt_username_r.setText(username)
        txt_name_user.setText(name)
        txt_email_user.setText(email)
        txt_phone_user.setText(phone)
    }
    private fun irUpdateAccount(){
        var intentExplicito = Intent(this, UpdateAccount::class.java)
        this.startActivity(intentExplicito)
    }
    private fun irLoginActivity(){
        var editor = preferences.edit()
        editor.putInt("userID", 0).apply()



        var intentExplicito = Intent(this, MainActivity::class.java)
        this.startActivity(intentExplicito)
    }
}