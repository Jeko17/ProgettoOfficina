package com.android.officina.persistence.sql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.officina.entities.Auto
import com.android.officina.entities.AutoInfo
import com.android.officina.persistence.dao.AutoDao

@Dao
interface SqlAutoDao:AutoDao {
    @Query("SELECT * FROM cars")
    override fun getAll(): List<Auto>

    @Query("SELECT * FROM cars WHERE id = :acronym")
    fun infoCars(acronym: Long): Auto

    @Query("SELECT c.id, telaio, model, targa, ifnull(customer,'Cliente non presente o cancellato dal database') as customer , cu.surname || ' ' || cu.name as name_complete " +
            "FROM cars c  " +
            "LEFT JOIN customers cu ON cu.id=c.customer " +
            "WHERE c.id = :acronym")
    fun getInfoCarsTest(acronym: Long): AutoInfo

    @Insert
    override fun add(auto: Auto)

    @Delete
    override fun delete(auto: Auto)

}