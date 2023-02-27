package com.android.officina.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*


// Creo la taballa customer con i dati del cliente dell'officina
@Entity(tableName = "customers")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val surname: String,
    val email:String
)