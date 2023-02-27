package com.android.officina.persistence.dao

import androidx.room.Dao
import com.android.officina.entities.Cliente

interface ClienteDao {

    fun getAll(): List<Cliente> // Funzione che restituisce tutti i clienti
    fun add(cliente: Cliente) // Funzione per l'aggiunta di un nuovo cliente
    fun delete(cliente: Cliente) // Funzione per la rimozione di un cliente
}