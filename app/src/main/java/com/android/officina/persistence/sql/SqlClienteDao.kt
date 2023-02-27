package com.android.officina.persistence.sql

import androidx.room.*
import com.android.officina.entities.Cliente
import com.android.officina.persistence.dao.ClienteDao

@Dao
interface SqlClienteDao: ClienteDao {

    @Query("SELECT * FROM customers ORDER BY surname, name")
    override fun getAll(): List<Cliente>

    @Query("SELECT * FROM customers WHERE id = :acronym")
    fun infoCustomers(acronym: Long): Cliente

    @Insert
    override fun add(cliente: Cliente)

    @Delete
    override fun delete(cliente: Cliente)

}