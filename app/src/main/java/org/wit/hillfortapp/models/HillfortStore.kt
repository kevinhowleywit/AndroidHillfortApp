package org.wit.hillfortapp.models
import org.wit.hillfortapp.models.HillfortMemStore

interface HillfortStore{
    fun findAll():List<HillfortModel>
    fun create(hillfort:HillfortModel)
    fun update(hillfort: HillfortModel)
}