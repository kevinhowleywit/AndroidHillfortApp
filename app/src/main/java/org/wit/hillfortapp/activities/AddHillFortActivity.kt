package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add_hill_fort.*
import kotlinx.android.synthetic.main.activity_add_hill_fort.AddHf
import kotlinx.android.synthetic.main.activity_add_hill_fort.addImgBtn
import kotlinx.android.synthetic.main.activity_add_hill_fort.hfDesc
import kotlinx.android.synthetic.main.activity_add_hill_fort.hfName
import kotlinx.android.synthetic.main.activity_add_hill_fort.hillfortImage
import kotlinx.android.synthetic.main.activity_add_hill_fort.locationBtn
import kotlinx.android.synthetic.main.activity_add_hillfort_v2.*
import kotlinx.android.synthetic.main.activity_hill_fort_list.*
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.card_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.hillfortapp.R
import org.wit.hillfortapp.helpers.readImage
import org.wit.hillfortapp.helpers.readImageFromPath
import org.wit.hillfortapp.helpers.showImagePicker
import org.wit.hillfortapp.main.MainApp
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.Location

class AddHillFortActivity : AppCompatActivity(),AnkoLogger {


    var hillfort= HillfortModel()
    lateinit var app:MainApp
    var edit=false
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST=2
    //var location = Location(52.3483333, -7.1252777,15f)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hillfort_v2)
        app =application as MainApp

        if(intent.hasExtra("hillfort_edit")){
            edit=true
            hillfort=intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
            hfName.setText(hillfort.name)
            hfDesc.setText(hillfort.description)
            locationBtn.setText(R.string.change_location)

            AddHf.setText(R.string.save_hillfort)
            addImgBtn.setText(R.string.save_image)
            hillfortImage.setImageBitmap(readImageFromPath(this, hillfort.image))

        }

        AddHf.setOnClickListener(){
            info{"pressed addhf"}

            val HillName=hfName.text.toString()
            val HillDescription=hfDesc.text.toString()

            hillfort.name=HillName
            hillfort.description=HillDescription

            if(HillName.isEmpty() || HillDescription.isEmpty()){
                toast("Please fill out all fields")
            }

            else{
                if(edit){

                    app.hillforts.update(hillfort.copy())
                }
                else{
                    app.hillforts.create(hillfort.copy())
                }


                /*hillfort.name=HillName
                hillfort.description=HillDescription
                app.hillforts.create(hillfort.copy())
                info { "Hillfort Name :$HillName" }
                info { "Hillfort Description: $HillDescription" }*/

                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
        }

        addImgBtn.setOnClickListener(){
            info("add image pressed")
            showImagePicker(this, IMAGE_REQUEST)

        }

        locationBtn.setOnClickListener{
            info("set location pressed")
            val location = Location(52.3483333, -7.1252777,15f)

            if(hillfort.zoom != 0f){
                location.lat=hillfort.lat
                location.lng=hillfort.lng
                location.zoom=hillfort.zoom

            }
            startActivityForResult(intentFor<MapActivity>().putExtra("location", location), LOCATION_REQUEST)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    hillfort.image = data.getData().toString()
                    hillfortImage.setImageBitmap(readImage(this,resultCode,data))
                    addImgBtn.setText(R.string.save_image)
                }

            }

            LOCATION_REQUEST -> {
                if(data != null){
                    val location = data.extras?.getParcelable<Location>("location")!!
                    hillfort.lat=location.lat
                    hillfort.lng=location.lng
                    hillfort.zoom=location.zoom


                }
            }
        }
    }


    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }*/


    /*override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }*/


}
