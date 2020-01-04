package org.wit.hillfortapp.activities.views.hillfortList

import org.jetbrains.anko.*
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
    fun getHillforts(){
        doAsync {
            val hillforts = app.hillforts.findAll()
            uiThread {
                view?.showHillforts(hillforts)
            }
        }
    }



    fun doEditHillfort(hillfort: HillfortModel) {
        view?.navigateTo(VIEW.HILLFORT,0,"hillfort_edit",hillfort)
    }

    fun doShowHillfortMap() {
        view?.navigateTo(VIEW.MAPS)
    }
    fun loadHillforts(){
        doAsync {
            val hillforts = app.hillforts.findAll()
            uiThread {
                view?.showHillforts(hillforts)
            }
        }
        //view?.showHillforts(app.hillforts.findAll())

    }
}