package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.R
import org.wit.hillfortapp.models.HillfortModel

class HillfortActivity : AppCompatActivity(),AnkoLogger {


    var hillfort= HillfortModel()
    var hillforts=ArrayList<HillfortModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort)
        //trying to clear activity history so when you press back it goes to login screen without going back to register on second press
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        addHfButton.setOnClickListener(){

            info{"Add hillfort button pressed"}

            val intent = Intent(this, AddHillFortActivity::class.java)
            startActivity(intent)


        }

        viewHfButton.setOnClickListener(){

            info{"view hillfort button pressed"}
            val intent = Intent(this, HillFortListActivity::class.java)
            startActivity(intent)
        }
    }
}
