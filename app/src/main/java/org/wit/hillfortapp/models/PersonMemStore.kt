package org.wit.hillfortapp.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class PersonMemStore:PersonStore,AnkoLogger{

    val people=ArrayList<PersonModel>()
    override fun findAll(): List<PersonModel> {
        return people
    }

    override fun create(person: PersonModel) {
        people.add(person)
    }

    fun logAll(){

        people.forEach{info("${it}")}
    }

    override fun update(person: PersonModel) {
        var foundPerson:PersonModel?=people.find { p -> p.id==person.id }

        if (foundPerson!= null){
            foundPerson.email=person.email
            foundPerson.password=foundPerson.password
            logAll()
        }
    }
}