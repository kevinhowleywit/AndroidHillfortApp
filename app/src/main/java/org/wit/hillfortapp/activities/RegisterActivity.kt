package org.wit.hillfortapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailText
import kotlinx.android.synthetic.main.activity_login.passwordText
import kotlinx.android.synthetic.main.activity_login.regBtn
import kotlinx.android.synthetic.main.activity_register.*
import org.wit.hillfortapp.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.PersonModel

class RegisterActivity : AppCompatActivity(), AnkoLogger{

    var person=PersonModel()
    lateinit var app: MainApp




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        regBtn.setOnClickListener(){
            info("register button pressed from register activity")

            val email=emailText.text.toString()
            val password=passwordText.text.toString()
            val confPassword=pwPrompt2.text.toString()

            if (email.isEmpty() || password.isEmpty() || confPassword.isEmpty()){
                toast("Please fill out all fields")

            }


        }
    }
}
