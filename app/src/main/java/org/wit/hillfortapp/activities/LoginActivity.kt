package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillfortapp.R
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.PersonModel

class LoginActivity : AppCompatActivity() ,AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var hillfort= HillfortModel()
        var person= PersonModel()

        loginButton.setOnClickListener(){

            info("login button pressed" )
            val email=emailText.text.toString()
            val password=passwordText.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                info("login pressed :$email")
                toast("Please fill out all fields")
            }
            else{

                info { "got to the else loop" }

                //TODO login validation
                val intent = Intent(this, HillfortActivity::class.java)
                startActivity(intent)


            }


        }

        regBtn.setOnClickListener(){
            info("register button pressed from login screen" )
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }









    }




}
