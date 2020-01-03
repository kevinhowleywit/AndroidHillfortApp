package org.wit.hillfortapp.activities.views.editLocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import org.wit.hillfortapp.R

class EditLocationView : AppCompatActivity(), GoogleMap.OnMarkerDragListener,GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    //var location = Location()
    lateinit var presenter: EditLocationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        //location = intent.extras?.getParcelable<Location>("location")!!
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        presenter=
            EditLocationPresenter(
                this
            )

        mapFragment.getMapAsync{
            map=it
            map.setOnMarkerDragListener(this)
            map.setOnMarkerClickListener(this)
            presenter.initMap(map)
        }
    }
    override fun onMarkerDragStart(marker: Marker) {
    }
    override fun onMarkerDrag(marker: Marker) {
    }
    override fun onMarkerDragEnd(marker: Marker) {
        presenter.doUpdateLocation(marker.position.latitude,marker.position.longitude,map.cameraPosition.zoom)
    }
    override fun onBackPressed() {
        presenter.doOnBackPressed()
    }
    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doUpdateMarker(marker)
        return false
    }
    /*
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val loc = LatLng(location.lat, location.lng)
        map.setOnMarkerClickListener(this)

        val options = MarkerOptions()
            .title("Hillfort")
            .snippet("GPS : " + loc.toString())
            .draggable(true)
            .position(loc)
        map.addMarker(options)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, location.zoom))
        map.setOnMarkerDragListener(this)

    }
*/


}