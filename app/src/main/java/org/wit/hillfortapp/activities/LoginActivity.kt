package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillfortapp.R
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.PersonModel
import java.lang.Exception
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() ,AnkoLogger{

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var hillfort= HillfortModel()
        
        loginButton.setOnClickListener(){

            app = application as MainApp


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
                try {




                }

                catch (e: Exception){
                    toast("Account not found")
                }


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

    override fun onBackPressed() {
        super.onBackPressed()


    }




}
