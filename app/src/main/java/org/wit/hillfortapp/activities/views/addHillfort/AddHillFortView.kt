package org.wit.hillfortapp.activities.views.addHillfort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

class AddHillFortView : BaseView(),AnkoLogger {

    //lateinit var app:MainApp
    //var edit=false
    //val IMAGE_REQUEST = 1
    //val LOCATION_REQUEST=2
    //var location = Location(52.3483333, -7.1252777,15f)
    lateinit var map:GoogleMap
    lateinit var presenter: AddHillfortPresenter
    var hillfort= HillfortModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hillfort_v2)

        presenter=initPresenter(AddHillfortPresenter(this)) as AddHillfortPresenter
        addMapView.onCreate(savedInstanceState)
        addMapView.getMapAsync{
            map=it
            presenter.doConfigureMap(map)
        }

        addImgBtn.setOnClickListener{presenter.doSelectImage()}
        locationBtn.setOnClickListener{presenter.doSetLocation()}
        /*
        addOtherImageBtn.setOnClickListener(){
            info("pressed add second image")
            //showImagePicker(this,IMAGE_REQUEST)
        }*/

        /*
        locationBtn.setOnClickListener{
            info("set location pressed")
            val location = Location(52.3483333, -7.1252777,15f)

            if(hillfort.zoom != 0f){
                location.lat=hillfort.lat
                location.lng=hillfort.lng
                location.zoom=hillfort.zoom

            }
            startActivityForResult(intentFor<MapActivity>().putExtra("location", location), LOCATION_REQUEST)
        }*/

    }
    override fun showHillfort(hillfort:HillfortModel){
        hfName.setText(hillfort.name)
        hfDesc.setText(hillfort.description)
        hillfortImage.setImageBitmap(readImageFromPath(this,hillfort.image))
        if(hillfort.image != null){
            addImgBtn.setText(R.string.save_image)
        }
            //AddHf.setText(R.string.save_hillfort)

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

    override fun onBackPressed() {
        presenter.doCancel()
    }


}
