package com.krishanshamod.simple_android_application_3.database

import androidx.room.*
import com.krishanshamod.simple_android_application_3.model.Location

@Dao
interface LocationDao {
    @Query("SELECT * FROM location")
    fun getAll(): List<Location>

    @Query("SELECT * FROM location WHERE id = :id")
    fun getById(id: Int): Location

    @Insert
    fun insertAll(vararg locations: Location)

    @Delete
    fun delete(location: Location)

    @Update
    fun update(location: Location)

}