package org.wit.hillfortapp.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortMemStore
import org.wit.hillfortapp.models.HillfortModel

class MainApp : Application(), AnkoLogger {


    val hillforts = HillfortMemStore()
    override fun onCreate() {
        super.onCreate()
        info("Hillfort started")
        //hillforts.add(HillfortModel("One","About one"))
        //hillforts.add(HillfortModel("2","About 2"))

    }
}