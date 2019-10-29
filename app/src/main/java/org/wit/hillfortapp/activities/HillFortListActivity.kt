package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import org.wit.hillfortapp.R
import org.wit.hillfortapp.main.MainApp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_hill_fort_list.*
import kotlinx.android.synthetic.main.card_hillfort.view.*
import org.jetbrains.anko.intentFor
import org.wit.hillfortapp.models.HillfortModel


class HillFortListActivity : AppCompatActivity(),HillfortListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var app:MainApp

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hill_fort_list)
        app=application as MainApp

        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=HillfortAdapter(app.hillforts.findAll(),this)
    }

    override fun onHillfortClick(hillfort: HillfortModel) {
        //startActivityForResult(intentFor<AddHillFortActivity>(),0)


        startActivityForResult(intentFor<AddHillFortActivity>().putExtra("hillfort_edit", hillfort), 0)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
/*
class HillfortAdapter constructor(private var hillforts: List<HillfortModel>) :
    RecyclerView.Adapter<HillfortAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_hillfort,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val hillfort = hillforts[holder.adapterPosition]
        holder.bind(hillfort)
    }

    override fun getItemCount(): Int = hillforts.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hillfort: HillfortModel) {
            itemView.hillfortTitle.text = hillfort.name
            itemView.description.text = hillfort.description
        }
    }

}*/