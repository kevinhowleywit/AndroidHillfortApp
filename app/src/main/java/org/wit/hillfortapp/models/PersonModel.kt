package org.wit.hillfortapp.models

data class PersonModel(var id:Long=0,var email: String = "",var password:String="", var hillforts:ArrayList<HillfortModel> = ArrayList())
