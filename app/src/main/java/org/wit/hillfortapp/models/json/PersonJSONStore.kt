package org.wit.hillfortapp.models.json
/*
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.hillfortapp.helpers.*
import java.util.*

val JSON_FILE = "people.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<PersonModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PlacemarkJSONStore : PersonStore, AnkoLogger {

    val context: Context
    var people = mutableListOf<PersonModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PersonModel> {
        return people
    }

    override fun create(person: PersonModel) {
        person.id = generateRandomId()
        people.add(person)
        serialize()
    }


    override fun update(person: PersonModel) {

    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(people, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        people = Gson().fromJson(jsonString, listType)
    }
}

*/