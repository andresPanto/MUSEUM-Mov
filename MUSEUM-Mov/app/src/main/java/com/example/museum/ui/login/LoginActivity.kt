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


        preferences =
            getSharedPreferences(EnvironmentVariables.prefsCredentialsName, Context.MODE_PRIVATE)
        activity = intent.getIntExtra("activityID", 0)

        val username = findViewById<EditText>(R.id.txt_username_log)
        val password = findViewById<EditText>(R.id.txt_password_log)
        val login = findViewById<Button>(R.id.btn_log_in)
        val loading = findViewById<ProgressBar>(R.id.loading)
        val boton_registro = findViewById<Button>(R.id.btn_sing_up)
//        val boton_lo = findViewById<Button>(R.id.btn_log_in)

        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
                if (username.length() == 0) {
                    Log.i("login", "falta username ${username.toString()}")
                } else if (password.length() == 0) {
                    Log.i("login", "falta password ${password.toString()}")
                } else {

                    var userExists: Boolean =
                        findUser(username.text.toString(), password.text.toString())
                    if (userExists) {
                        goHomeScreen()
                    } else {
                        Log.i("Log in", "fail")
                    }
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

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
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