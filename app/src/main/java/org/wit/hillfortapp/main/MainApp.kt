package org.wit.hillfortapp.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.*
import org.wit.hillfortapp.models.firebase.HillfortFireStore
import org.wit.hillfortapp.models.mem.HillfortMemStore
import org.wit.hillfortapp.models.mem.PersonMemStore
import org.wit.hillfortapp.rooms.HillfortStoreRoom

class MainApp : Application(), AnkoLogger {

    lateinit var hillforts:HillfortStore
    //lateinit var people:PersonModel
    val people= PersonMemStore()


    override fun onCreate() {
        super.onCreate()
        info("Hillfort started")
        //hillforts=HillfortJSONStore(applicationContext)
        //hillforts= HillfortStoreRoom(applicationContext)
        hillforts=HillfortFireStore(applicationContext)



    }


}