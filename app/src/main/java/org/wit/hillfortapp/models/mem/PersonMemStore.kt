package org.wit.hillfortapp.models.mem

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.PersonModel
import org.wit.hillfortapp.models.PersonStore

class PersonMemStore: PersonStore,AnkoLogger{

    val people=ArrayList<PersonModel>()

    var lastId = 0L
    internal fun getId(): Long {
        return lastId++
    }


    override fun findAll(): ArrayList<PersonModel> {
        return people
    }

    override fun create(person: PersonModel) {
        person.id=getId()
        people.add(person)
        logAll()
    }

    fun logAll(){

        people.forEach{info("${it}")}
    }

    override fun update(person: PersonModel) {
        var foundPerson: PersonModel?=people.find { p -> p.id==person.id }

        if (foundPerson!= null){
            foundPerson.email=person.email
            foundPerson.password=foundPerson.password
            logAll()
        }
    }
}