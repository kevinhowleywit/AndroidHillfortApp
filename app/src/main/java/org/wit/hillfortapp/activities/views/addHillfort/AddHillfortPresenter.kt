package org.wit.hillfortapp.activities.views.addHillfort


import android.content.Intent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.intentFor
import org.wit.hillfortapp.activities.views.*
import org.wit.hillfortapp.activities.views.editLocation.EditLocationView
import org.wit.hillfortapp.helpers.showImagePicker
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.Location
import org.wit.hillfortapp.models.HillfortModel

class AddHillfortPresenter(view: BaseView):BasePresenter(view) {

    //val IMAGE_REQUEST = 1
    //val LOCATION_REQUEST = 2

    var hillfort = HillfortModel()
    var defaultLocation = Location(52.245696, -7.139102, 15f)
    //var app: MainApp
    var edit = false;
    var map: GoogleMap? = null


    init {
        if (view.intent.hasExtra("hillfort_edit")) {
            edit = true
            hillfort = view.intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
            view.showHillfort(hillfort)
        }
        else{
            hillfort.lat=defaultLocation.lat
            hillfort.lng=defaultLocation.lng
        }
    }

    fun doAddOrSave(title: String, description: String) {
        hillfort.name = title
        hillfort.description = description
        if (edit) {
            app.hillforts.update(hillfort)
        } else {
            app.hillforts.create(hillfort)
        }
        view?.finish()
    }

    fun doCancel() {
        view?.finish()
    }

    fun doDelete() {
        app.hillforts.delete(hillfort)
        view?.finish()
    }

    fun doSelectImage() {
        view?.let {
            showImagePicker(view!!, IMAGE_REQUEST)
        }
    }

    fun doSetLocation() {
        if(edit==false){
            view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST,"location",defaultLocation)

        }
        else{
            view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST,"location",Location(hillfort.lat,hillfort.lng,hillfort.zoom))
        }
        /*if (hillfort.zoom != 0f) {
            location.lat = hillfort.lat
            location.lng = hillfort.lng
            location.zoom = hillfort.zoom
        }
        view.startActivityForResult(view.intentFor<EditLocationView>().putExtra("location", location), LOCATION_REQUEST)*/
    }
    fun doConfigureMap(m: GoogleMap) {
        map = m
        locationUpdate(hillfort.lat, hillfort.lng)
    }
    fun locationUpdate(lat: Double, lng: Double) {
        hillfort.lat = lat
        hillfort.lng = lng
        hillfort.zoom = 15f
        map?.clear()
        map?.uiSettings?.setZoomControlsEnabled(true)
        val options = MarkerOptions().title(hillfort.name).position(LatLng(hillfort.lat, hillfort.lng))
        map?.addMarker(options)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(hillfort.lat, hillfort.lng), hillfort.zoom))
        view?.showHillfort(hillfort)
    }



    override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            IMAGE_REQUEST -> {
                hillfort.image = data.data.toString()
                view?.showHillfort(hillfort)
            }
            LOCATION_REQUEST -> {
                val location = data.extras?.getParcelable<Location>("location")!!
                hillfort.lat = location.lat
                hillfort.lng = location.lng
                hillfort.zoom = location.zoom
                locationUpdate(hillfort.lat,hillfort.lng)
            }
        }
    }
}