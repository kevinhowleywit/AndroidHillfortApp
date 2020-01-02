package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import org.wit.hillfortapp.R
import org.wit.hillfortapp.main.MainApp
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_hill_fort_list.*
import kotlinx.android.synthetic.main.card_hillfort.view.*
import org.jetbrains.anko.intentFor
import org.wit.hillfortapp.models.HillfortModel


class HillFortListView : AppCompatActivity(),HillfortListener {

    //lateinit var app:MainApp
    lateinit var presenter: HillfortListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hill_fort_list)
        presenter= HillfortListPresenter(this)
        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=HillfortAdapter(presenter.getHillforts(),this)
        recyclerView.adapter?.notifyDataSetChanged()

        loadHillforts()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hf_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> {
                presenter.doAddHillfort()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onHillfortClick(hillfort: HillfortModel) {
        //startActivityForResult(intentFor<AddHillFortActivity>(),0)
        //startActivityForResult(intentFor<AddHillFortActivity>().putExtra("hillfort_edit", hillfort), 0)
        presenter.doEditHillfort(hillfort)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadHillforts()
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadHillforts() {
        showHillforts(presenter.getHillforts())
    }

    fun showHillforts (hillforts: List<HillfortModel>) {
        recyclerView.adapter = HillfortAdapter(hillforts, this)
        recyclerView.adapter?.notifyDataSetChanged()
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