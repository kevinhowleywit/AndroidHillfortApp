package org.wit.hillfortapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_hill_fort.*
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillfortapp.R
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.HillfortModel

class AddHillFortActivity : AppCompatActivity(),AnkoLogger {


    var hillfort= HillfortModel()
    lateinit var app:MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hill_fort)
        app =application as MainApp

        AddHf.setOnClickListener(){
            info{"pressed addhf"}

            val HillName=hfName.text.toString()
            val HillDescription=hfDesc.text.toString()

            if(HillName.isEmpty() || HillDescription.isEmpty()){
                toast("Please fill out all fields")
            }

            else{
                hillfort.name=HillName
                hillfort.description=HillDescription
                app.hillforts.add(hillfort.copy())
                info { "Hillfort Name :$HillName" }
                info { "Hillfort Description: $HillDescription" }
                for(i in app!!.hillforts.indices){
                    info("Hillfort[$i]:${app!!.hillforts[i]}")
                }



            }
        }
    }
}
