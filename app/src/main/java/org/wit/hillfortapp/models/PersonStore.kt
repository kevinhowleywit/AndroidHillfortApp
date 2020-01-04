package org.wit.hillfortapp.models

interface PersonStore{
    fun findAll():List<PersonModel>
    fun create(person:PersonModel)
    fun update(person: PersonModel)

}