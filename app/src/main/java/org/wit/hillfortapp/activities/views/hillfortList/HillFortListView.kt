package org.wit.hillfortapp.activities.views.hillfortList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import org.wit.hillfortapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_hill_fort_list.*
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.activities.views.BaseView

class HillFortListView : BaseView(), HillfortListener {

    //lateinit var app:MainApp
    lateinit var presenter: HillfortListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hill_fort_list)

        presenter=initPresenter(HillfortListPresenter(this)) as HillfortListPresenter

        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        //recyclerView.adapter= HillfortAdapter(presenter.loadHillforts(), this)
        //recyclerView.adapter?.notifyDataSetChanged()

        presenter.loadHillforts()
    }
    override fun showHillforts (hillforts: List<HillfortModel>) {
        recyclerView.adapter =
            HillfortAdapter(
                hillforts,
                this
            )
        recyclerView.adapter?.notifyDataSetChanged()
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
        presenter.loadHillforts()
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    /*private fun loadHillforts() {
        showHillforts(presenter.getHillforts())
    }*/





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