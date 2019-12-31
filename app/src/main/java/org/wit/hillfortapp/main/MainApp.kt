package org.wit.hillfortapp.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.*

class MainApp : Application(), AnkoLogger {

    lateinit var hillforts:HillfortStore
    //lateinit var people:PersonModel
    val people=PersonMemStore()


    override fun onCreate() {
        super.onCreate()
        info("Hillfort started")
        //hillforts=HillfortJSONStore(applicationContext)
        hillforts=HillfortMemStore()



    }


}