package com.android.officina.persistence.dao

import com.android.officina.entities.Interventi

interface InterventiDao {

    fun getAll(): List<Interventi> // Funzione che restituisce tutti gli interventi
    fun add(interventi: Interventi) // Funzione per l'aggiunta di un nuovo intervento
    fun delete(interventi: Interventi) // Funzione per la rimozione di un intervento
}