package com.example.museum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.museum.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_user_account.*

class UserAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)

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
        var intentExplicito = Intent(this, LoginActivity::class.java)
        this.startActivity(intentExplicito)
    }
}