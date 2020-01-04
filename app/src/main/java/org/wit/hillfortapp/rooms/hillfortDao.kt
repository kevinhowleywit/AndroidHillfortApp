package org.wit.hillfortapp.rooms


import androidx.room.*
import org.wit.hillfortapp.models.HillfortModel

@Dao
interface hillfortDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(hillfort: HillfortModel)

    @Query("SELECT * FROM HillfortModel")
    fun findAll(): List<HillfortModel>

    @Query("select * from HillfortModel where id = :id")
    fun findById(id: Long): HillfortModel

    @Update
    fun update(hillfort: HillfortModel)

    @Delete
    fun deleteHillfort(hillfort: HillfortModel)
}
