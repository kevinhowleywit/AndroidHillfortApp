package org.wit.hillfortapp.activities.views.hillfortList

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.hillfortapp.activities.views.map.HillfortMapsView
import org.wit.hillfortapp.activities.views.addHillfort.AddHillFortView
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.HillfortModel

class HillfortListPresenter(val view: HillFortListView) {

    var app: MainApp

    init {
        app = view.application as MainApp
    }

    fun getHillforts() = app.hillforts.findAll()

    fun doAddHillfort() {
        view.startActivityForResult<AddHillFortView>(0)
    }

    fun doEditHillfort(hillfort: HillfortModel) {
        view.startActivityForResult(view.intentFor<AddHillFortView>().putExtra("hillfort_edit", hillfort), 0)
    }

    fun doShowHillfortMap() {
        view.startActivity<HillfortMapsView>()
    }
}