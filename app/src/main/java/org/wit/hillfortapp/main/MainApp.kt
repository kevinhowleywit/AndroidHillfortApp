package org.wit.hillfortapp.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortModel

class MainApp : Application(), AnkoLogger {


    val hillforts = ArrayList<HillfortModel>()
    override fun onCreate() {
        super.onCreate()
        info("Hillfort started")
    }
}