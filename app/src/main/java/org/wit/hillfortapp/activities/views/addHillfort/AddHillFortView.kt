package org.wit.hillfortapp.activities.views.addHillfort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import kotlinx.android.synthetic.main.activity_add_hill_fort.AddHf
import kotlinx.android.synthetic.main.activity_add_hill_fort.addImgBtn
import kotlinx.android.synthetic.main.activity_add_hill_fort.hfDesc
import kotlinx.android.synthetic.main.activity_add_hill_fort.hfName
import kotlinx.android.synthetic.main.activity_add_hill_fort.hillfortImage
import kotlinx.android.synthetic.main.activity_add_hill_fort.locationBtn
import kotlinx.android.synthetic.main.activity_add_hillfort_v2.*
import kotlinx.android.synthetic.main.activity_hillfort_maps.*
import kotlinx.android.synthetic.main.card_hillfort.*
import kotlinx.android.synthetic.main.content_hillfort_maps.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillfortapp.R
import org.wit.hillfortapp.activities.views.BaseView
import org.wit.hillfortapp.helpers.readImageFromPath
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.Location

class AddHillFortView : BaseView(),AnkoLogger {

    //lateinit var app:MainApp
    //var edit=false
    //val IMAGE_REQUEST = 1
    //val LOCATION_REQUEST=2
    //var location = Location(52.3483333, -7.1252777,15f)
    //lateinit var map:GoogleMap
    lateinit var presenter: AddHillfortPresenter
    var hillfort= HillfortModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hillfort_v2)

        presenter=initPresenter(AddHillfortPresenter(this)) as AddHillfortPresenter

        addMapView.onCreate(savedInstanceState)
        addMapView.getMapAsync{presenter.doConfigureMap(it)
        it.setOnMapClickListener { presenter.doSetLocation() }}

        addImgBtn.setOnClickListener{presenter.doSelectImage()}
        //locationBtn.setOnClickListener{presenter.doSetLocation()}


    }
    override fun showHillfort(hillfort:HillfortModel){
        hfName.setText(hillfort.name)
        hfDesc.setText(hillfort.description)
        Glide.with(this).load(hillfort.image).into(hillfortImage);
        if(hillfort.image != null){
            addImgBtn.setText(R.string.save_image)
        }
        this.showLocation(hillfort.location)
        //lat.setText("%.6f".format(hillfort.lat))
        //lng.setText("%.6f".format(hillfort.lng))
            //AddHf.setText(R.string.save_hillfort)

    }
    override fun showLocation(location: Location) {
        lat.setText("%.6f".format(location.lat))
        lng.setText("%.6f".format(location.lng))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        if (presenter.edit) menu.getItem(1).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                presenter.doCancel()}
            R.id.item_delete ->{
                presenter.doDelete()
            }
            R.id.item_save ->{
                if(hfName.toString().isEmpty()){
                    toast("Enter Title")
                }
                else{
                    presenter.doAddOrSave(hfName.text.toString(),hfDesc.text.toString())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null){
            presenter.doActivityResult(requestCode,resultCode,data)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        addMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        addMapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        addMapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        addMapView.onResume()
        presenter.doResartLocationUpdates()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        addMapView.onSaveInstanceState(outState)
    }




}
