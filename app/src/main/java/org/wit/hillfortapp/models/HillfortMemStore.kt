package org.wit.hillfortapp.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class HillfortMemStore:HillfortStore,AnkoLogger{

    val hillforts=ArrayList<HillfortModel>()
    override fun findAll(): List<HillfortModel> {
        return hillforts
    }

    override fun create(hillfort: HillfortModel) {
        hillforts.add(hillfort)
    }

    fun logAll(){

        hillforts.forEach{info("${it}")}
    }

    override fun update(hillfort: HillfortModel) {
        var foundHillfort:HillfortModel?=hillforts.find { p -> p.id==hillfort.id }

        if (foundHillfort!= null){
            foundHillfort.name=hillfort.name
            foundHillfort.description=hillfort.description
            foundHillfort.image=hillfort.image
            foundHillfort.lat=hillfort.lat
            foundHillfort.lng=hillfort.lng
            foundHillfort.zoom=hillfort.zoom
            logAll()
        }
    }
}