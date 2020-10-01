package com.example.museum

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.museum.environment.EnvironmentVariables
import com.example.museum.httpHandlers.UserHTTPHandler
import com.example.museum.models.User
import com.example.museum.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.activity_user_account.*

class RegisterUser : AppCompatActivity() {
    val userHTTPHandler: UserHTTPHandler = UserHTTPHandler()

    private lateinit var preferences: SharedPreferences
    private var userID: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)


        preferences = getSharedPreferences(
            EnvironmentVariables.prefsCredentialsName,
            Context.MODE_PRIVATE)
        userID = preferences.getInt("userID", 0)

        if (userID != 0) {
            val user: User? = UserHTTPHandler().getOne(userID)
            if (user != null) {
                setUpdateEnvironment(user)

            }else{
                Log.i("User", "No user in API")
            }
        }else{
            Log.i("User", "No user credentials")
            btn_sing_me_up.setOnClickListener{
                getValues()
            }
            btn_log_in_r.setOnClickListener{
                irLoginActivity()
            }
        }

        iv_monalisa.setOnClickListener { selectButton(0) }
        iv_scream.setOnClickListener { selectButton(1) }
        iv_marilyn.setOnClickListener { selectButton(2) }
        iv_van_gogh.setOnClickListener { selectButton(3) }


        setUpImages()
    }

    private fun setUpdateEnvironment(user: User){
        textView5.text = "Update Your Account"
        textView6.text = "Change your information"
        txt_user_full_name.setText(user.fullName)
        txt_email.setText(user.email)
        txt_phone_number.setText(user.phoneNumber)
        txt_uesrname_reg.setText(user.username)
        txt_password.setText(user.password)
        txt_password_conf.setText(user.password)
        val pictureID: Int = EnvironmentVariables.profilePictures.indexOf(user.imagePath)
        Log.i("PictureID", "$pictureID")
        when(pictureID){
            0 -> rg_profile_pictures.check(R.id.radio0)
            1 -> rg_profile_pictures.check(R.id.radio01)
            2 -> rg_profile_pictures.check(R.id.radio02)
            3 -> rg_profile_pictures.check(R.id.radio03)
            else -> rg_profile_pictures.check(R.id.radio0)

        }
        btn_sing_me_up.text = "Update"
        textView10.text = "Not updating?"
        btn_log_in_r.text = "Go back"


        txt_user_full_name.isEnabled = false
        txt_uesrname_reg.isEnabled = false
        txt_password.isEnabled = false
        txt_password_conf.isEnabled = false

        btn_log_in_r.setOnClickListener{ goBack()}
        btn_sing_me_up.setOnClickListener{ update() }


    }

    private fun update(){
        val intent: Intent = Intent(this, UserAccount::class.java)
        val email: String = txt_email.text.toString()
        val phone: String = txt_phone_number.text.toString()
        val radioButtonID: Int = rg_profile_pictures.checkedRadioButtonId
        val selectedRadioButton: RadioButton =
            rg_profile_pictures.findViewById<RadioButton>(radioButtonID)
        val index: Int = rg_profile_pictures.indexOfChild(selectedRadioButton)

        val imagePath: String = EnvironmentVariables.profilePictures[index]
        val params: List<Pair<String, Any>> = arrayListOf(
            "email" to email,
            "phoneNumber" to phone,
            "imagePath" to imagePath
        )
        val user: User? = userHTTPHandler.updateOne(params, userID)
        if (user != null) {
            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG)
            startActivity(intent)
            finish()
        }

    }

    private fun goBack(){
        var intentExplicito = Intent(this, UserAccount::class.java)
        this.startActivity(intentExplicito)
        finish()
    }


    private fun setUpImages(){
        val imagePaths: ArrayList<String> = EnvironmentVariables.profilePictures
        Glide.with(this).load(imagePaths[0]).into(iv_monalisa)
        Glide.with(this).load(imagePaths[1]).into(iv_scream)
        Glide.with(this).load(imagePaths[2]).into(iv_marilyn)
        Glide.with(this).load(imagePaths[3]).into(iv_van_gogh)
    }

    private fun irLoginActivity(){
        var intentExplicito = Intent(this, LoginActivity::class.java)
        this.startActivity(intentExplicito)
    }

    private fun getValues(){
        Log.i("GetValues", "En la funcion")
        var estado_var = true
        var full_name : String = txt_user_full_name.text.toString()
        var email_user: String = txt_email.text.toString()
        var phone_number:String =txt_phone_number.text.toString()
        var username =txt_uesrname_reg.text.toString()
        var password = txt_password.text.toString()
        var password_confirmation=txt_password_conf.text.toString()

        if(full_name.length==0){
            estado_var = false
            Toast.makeText(this, "Debe ingresar un nombre", Toast.LENGTH_LONG)
        }else
            if(email_user.length==0){
            estado_var = false
            Toast.makeText(this, "Debe ingresar un email", Toast.LENGTH_LONG)
        }else
            if(phone_number.length==0){
            estado_var = false
            Toast.makeText(this, "Debe ingresar un numero", Toast.LENGTH_LONG)
        }else
            if(username.length==0){
            estado_var = false
            Toast.makeText(this, "Debe ingresar un nombre de usuario", Toast.LENGTH_LONG)
        }else
            if(password.length==0){
            estado_var = false
            Toast.makeText(this, "Debe ingresar un password", Toast.LENGTH_LONG)
        }else
            if(password_confirmation.length==0){
            estado_var = false
            Toast.makeText(this, "Debe ingresar un password", Toast.LENGTH_LONG)
        }else if(!password.toString().equals(password_confirmation.toString())){
            estado_var = false
            Toast.makeText(this, "El password debe ser igual", Toast.LENGTH_LONG)
        }
        if(estado_var) {
            Log.i("Crear", "Se puede crear")
            val radioButtonID: Int = rg_profile_pictures.checkedRadioButtonId
            val selectedRadioButton: RadioButton =
                rg_profile_pictures.findViewById<RadioButton>(radioButtonID)
            val index: Int = rg_profile_pictures.indexOfChild(selectedRadioButton)

            val imagePath: String = EnvironmentVariables.profilePictures[index]

            Log.i(
                "Valores Registro", "${full_name}," +
                        " ${email_user}," +
                        " ${phone_number}," +
                        " ${username}," +
                        " ${password}," +
                        " ${password_confirmation}, " +
                        "${imagePath}"
            )


            val params: List<Pair<String, Any>> = arrayListOf(
                "username" to username,
                "password" to password,
                "email" to email_user,
                "fullName" to full_name,
                "phoneNumber" to phone_number,
                "imagePath" to imagePath
            )
            val user: User? = userHTTPHandler.createOne(params)
            if (user != null) {
                Log.i("User creado", "${user.fullName}")
                Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG)
                val editor = preferences.edit()
                editor.putInt("userID", user.id)
                editor.commit()
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        }

    }


    fun selectButton(index: Int){
        val radioButton: RadioButton = rg_profile_pictures.getChildAt(index) as RadioButton
        rg_profile_pictures.check(radioButton.id)
    }

}