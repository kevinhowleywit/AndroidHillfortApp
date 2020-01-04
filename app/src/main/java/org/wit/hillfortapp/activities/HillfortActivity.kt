package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortModel
import org.jetbrains.anko.startActivity
import org.wit.hillfortapp.activities.views.addHillfort.AddHillFortView
import org.wit.hillfortapp.activities.views.hillfortList.HillFortListView
import org.wit.hillfortapp.activities.views.login.LoginView
import org.wit.hillfortapp.activities.views.map.HillfortMapsView
import org.wit.hillfortapp.main.MainApp


class HillfortActivity : AppCompatActivity(),AnkoLogger {

    lateinit var app: MainApp

    var hillfort = HillfortModel()
    var hillforts = ArrayList<HillfortModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(org.wit.hillfortapp.R.layout.activity_hillfort)
        val user = FirebaseAuth.getInstance().currentUser
        if (user !=null){
            emailTextView.setText("Welcome ${user.email}")
        }
        addHfButton.setOnClickListener() {

            info { "Add hillfort button pressed" }

            val intent = Intent(this, AddHillFortView::class.java)
            startActivity(intent)


        }

        viewHfButton.setOnClickListener() {

            info { "view hillfort button pressed" }
            val intent = Intent(this, HillFortListView::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(org.wit.hillfortapp.R.menu.menu_hf_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            org.wit.hillfortapp.R.id.item_map -> startActivity<HillfortMapsView>()
            org.wit.hillfortapp.R.id.item_logout -> {
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this, LoginView::class.java)
                this.startActivity(intent)
                app.hillforts.clear()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
