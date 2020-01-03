package org.wit.hillfortapp.activities.views.hillfortList

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.hillfortapp.activities.views.map.HillfortMapsView
import org.wit.hillfortapp.activities.views.addHillfort.AddHillFortView
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.activities.views.BaseView
import org.wit.hillfortapp.activities.views.BasePresenter
import org.wit.hillfortapp.activities.views.VIEW

class HillfortListPresenter(view: BaseView):BasePresenter(view) {


    fun doAddHillfort(){
        view?.navigateTo(VIEW.HILLFORT)
    }
    fun getHillforts() = app.hillforts.findAll()



    fun doEditHillfort(hillfort: HillfortModel) {
        view?.navigateTo(VIEW.HILLFORT,0,"hillfort_edit",hillfort)
    }

    fun doShowHillfortMap() {
        view?.navigateTo(VIEW.MAPS)
    }
    fun loadHillforts(){
        view?.showHillforts(app.hillforts.findAll())
    }
}