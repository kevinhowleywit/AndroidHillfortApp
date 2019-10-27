package org.wit.hillfortapp.models

data class PersonModel(var email: String = "",var password:String="", var hillforts:ArrayList<HillfortModel> = ArrayList())
