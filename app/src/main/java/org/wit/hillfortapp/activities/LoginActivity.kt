package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailText
import kotlinx.android.synthetic.main.activity_login.passwordText
import kotlinx.android.synthetic.main.activity_login.regBtn
import kotlinx.android.synthetic.main.activity_register.*
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

        app = application as MainApp

        var hillfort= HillfortModel()
        var people=ArrayList<PersonModel>()
        people=app.people.findAll()

        people.forEach{info("${it} from login acticity")}



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
                try {
                    val personToLogin = PersonModel()
                    personToLogin.email=email
                    personToLogin.password=password

                    if (people.contains(personToLogin)){
                        info("person: ${personToLogin.email}")

                        emailText.setText("")
                        passwordText.setText("")


                        val intent = Intent(this, HillfortActivity::class.java)
                        startActivity(intent)



                    }

                    else{
                        info("else loop of login validation")
                        toast("Account not found")

                    }

                }

                catch (e: Exception){
                    toast("Account not found")
                }


                //val intent = Intent(this, HillfortActivity::class.java)
                //startActivity(intent)


            }


        }

        regBtn.setOnClickListener(){
            info("register button pressed from login screen" )
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
    // on back pressed brings you to home instead of the splash screen
    override fun onBackPressed() {
        this.finishAffinity()


    }




}
