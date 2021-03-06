package org.wit.hillfortapp.activities.views

import android.content.Intent

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.AnkoLogger
import org.wit.hillfortapp.activities.HillfortActivity

import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.activities.views.editLocation.EditLocationView
import org.wit.hillfortapp.activities.views.map.HillfortMapsView
import org.wit.hillfortapp.activities.views.addHillfort.AddHillFortView
import org.wit.hillfortapp.activities.views.hillfortList.HillFortListView
import org.wit.hillfortapp.activities.views.login.LoginView
import org.wit.hillfortapp.models.Location

val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
    LOCATION, HILLFORT, MAPS, LIST,HILLFORTACTIVITY,LOGIN
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

    var basePresenter: BasePresenter? = null

    fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
        var intent = Intent(this, HillFortListView::class.java)
        when (view) {
            VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
            VIEW.HILLFORT -> intent = Intent(this, AddHillFortView::class.java)
            VIEW.MAPS -> intent = Intent(this, HillfortMapsView::class.java)
            VIEW.LIST -> intent = Intent(this, HillFortListView::class.java)
            VIEW.HILLFORTACTIVITY -> intent = Intent(this,HillfortActivity::class.java)
            VIEW.LOGIN -> intent=Intent(this,LoginView::class.java)
        }
        if (key != "") {
            intent.putExtra(key, value)
        }
        startActivityForResult(intent, code)
    }

    fun initPresenter(presenter: BasePresenter): BasePresenter {
        basePresenter = presenter
        return presenter
    }

    fun init(toolbar: Toolbar) {
        toolbar.title = title
        setSupportActionBar(toolbar)
    }


    override fun onDestroy() {
        basePresenter?.onDestroy()
        super.onDestroy()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            basePresenter?.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    open fun showHillfort(hillfort: HillfortModel) {}
    open fun showHillforts(hillforts: List<HillfortModel>) {}
    open fun showProgress() {}
    open fun hideProgress() {}
    open fun showLocation(location:Location) {}


}