package org.wit.hillfortapp.models.mem

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.HillfortStore

class HillfortMemStore: HillfortStore,AnkoLogger{

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
        var foundHillfort: HillfortModel?=hillforts.find { p -> p.id==hillfort.id }

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
    override fun findById(id:Long) : HillfortModel? {
        val foundHillfort: HillfortModel? = hillforts.find { it.id == id }
        return foundHillfort
    }

    override fun delete(hillfort: HillfortModel) {
        hillforts.remove(hillfort)
    }

}