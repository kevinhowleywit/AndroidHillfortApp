package org.wit.hillfortapp.activities.views.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.hillfortapp.activities.views.BasePresenter
import org.wit.hillfortapp.activities.views.BaseView
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.Location

class HillfortMapPresenter(view:BaseView):BasePresenter(view) {


    fun doPopulateMap(map: GoogleMap,hillforts:List<HillfortModel>) {

        map.uiSettings.setZoomControlsEnabled(true)
        hillforts.forEach{
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.name).position(loc).snippet("GPS : " + loc.toString())
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))

        }

    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as Long
        val hillfort = app.hillforts.findById(tag)
        if (hillfort != null) view?.showHillfort(hillfort)
    }
    fun loadHillforts() {
        view?.showHillforts(app.hillforts.findAll())
    }
}