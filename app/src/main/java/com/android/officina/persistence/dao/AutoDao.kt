package com.android.officina.persistence.dao

import com.android.officina.entities.Auto

interface AutoDao {

    fun getAll(): List<Auto> // Funzione che restituisce tutte le auto
    fun add(auto: Auto) // Funzione per l'aggiunta di una nuova auto
    fun delete(auto: Auto) // Funzione per la rimozione di un'auto
}