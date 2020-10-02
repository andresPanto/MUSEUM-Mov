package com.example.museum.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.museum.*

import com.example.museum.environment.EnvironmentVariables
import com.example.museum.httpHandlers.UserHTTPHandler
import com.example.museum.models.User

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var preferences: SharedPreferences
    private var activity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.txt_username_log)
        val password = findViewById<EditText>(R.id.txt_password_log)
        val login = findViewById<Button>(R.id.btn_log_in)
        val loading = findViewById<ProgressBar>(R.id.loading)
        val boton_registro = findViewById<Button>(R.id.btn_sing_up)


        login.setOnClickListener {
            if (username.length() == 0) {
                Log.i("login", "falta username ${username.toString()}")
                Toast.makeText(this, "Debe ingresar un username", Toast.LENGTH_LONG).show()
            } else if (password.length() == 0) {
                Log.i("login", "falta password ${password.toString()}")
                Toast.makeText(this, "Debe ingresar un password", Toast.LENGTH_LONG).show()
            } else {

                var userExists: Boolean =
                    findUser(username.text.toString(), password.text.toString())
                if (userExists) {
                    goHomeScreen()
                } else {
                    Log.i("Log in", "fail")
                    Toast.makeText(this, "Error en  el username o el password", Toast.LENGTH_LONG).show()
                }
            }
        }


        boton_registro.setOnClickListener {
            irRegistro()
        }
    }

    private fun findUser(username: String, password: String): Boolean {
        Log.i("Credenciales", "$username, $password")
        val user: ArrayList<User> = UserHTTPHandler().logIn(username, password)
        if (user.size == 0) {
            return false
        } else {
            val editor = preferences.edit()
            editor.putInt("userID", user[0].id)
            editor.commit()

            return true
        }
    }

    private fun irRegistro() {
        var intentExplicito = Intent(this, RegisterUser::class.java)
        this.startActivity(intentExplicito)
    }

    private fun goHomeScreen() {
        var intentExplicito: Intent
        if (activity == 0){
            intentExplicito = Intent(this, MainActivity::class.java)
            finish()
        }else{
            intentExplicito = Intent(this, BuyActivity::class.java)
            intentExplicito.putExtra("activityID", activity)
            finish()
        }

        this.startActivity(intentExplicito)
    }

}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}