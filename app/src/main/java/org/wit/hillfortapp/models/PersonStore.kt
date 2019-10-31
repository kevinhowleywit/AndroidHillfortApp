package org.wit.hillfortapp.models
import org.wit.hillfortapp.models.HillfortMemStore

interface PersonStore{
    fun findAll():List<PersonModel>
    fun create(person:PersonModel)
    fun update(person: PersonModel)
}