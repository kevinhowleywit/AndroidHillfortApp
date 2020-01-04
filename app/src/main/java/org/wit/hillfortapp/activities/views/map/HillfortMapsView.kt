package org.wit.hillfortapp.activities.views.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import org.wit.hillfortapp.R

import kotlinx.android.synthetic.main.activity_hillfort_maps.*
import kotlinx.android.synthetic.main.content_hillfort_maps.*
import org.wit.hillfortapp.activities.views.BaseView
import org.wit.hillfortapp.activities.views.map.HillfortMapPresenter
import org.wit.hillfortapp.helpers.readImageFromPath
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.Location

class HillfortMapsView :BaseView(),GoogleMap.OnMarkerClickListener {

    lateinit var map: GoogleMap
    //lateinit var app: MainApp
    lateinit var presenter: HillfortMapPresenter
    var location = Location()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort_maps)
        super.init(toolbar)


        presenter=initPresenter(HillfortMapPresenter(this)) as HillfortMapPresenter
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            map=it
            map.setOnMarkerClickListener(this)
            presenter.loadHillforts()
        }
    }

    override fun showHillfort(hillfort: HillfortModel) {
        currentTitle.text = hillfort.name
        currentDescription.text = hillfort.description
        Glide.with(this).load(hillfort.image).into(currentImage);
    }
    override fun showHillforts(hillforts: List<HillfortModel>) {
        presenter.doPopulateMap(map, hillforts)

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doMarkerSelected(marker)
        return true
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }




}
