package org.wit.hillfortapp.activities.views.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.Location

class HillfortMapPresenter(val view: HillfortMapsView) {

    var app: MainApp

    init {
        app = view.application as MainApp

    }

    fun doPopulateMap(map: GoogleMap) {

        map.uiSettings.setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(view)

        app.hillforts.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.name).position(loc).snippet("GPS : " + loc.toString())
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
        }
    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as Long
        val hillfort = app.hillforts.findById(tag)
        if (hillfort != null) view.showHillfort(hillfort)



    }
}