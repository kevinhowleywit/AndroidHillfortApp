package org.wit.hillfortapp.rooms

import androidx.room.Database
import androidx.room.RoomDatabase
import org.wit.hillfortapp.models.HillfortModel

@Database(entities = arrayOf(HillfortModel::class), version = 1,  exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun hillfortDao(): hillfortDao
}