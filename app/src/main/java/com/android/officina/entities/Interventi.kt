package com.android.officina.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

// Vado a creare la tabella interventions dove vado a registrare tutti gli interventi
// con chiave esterna l'id dell'auto
@Entity(tableName = "interventions", foreignKeys = [ForeignKey(entity = Auto::class,
                                                                parentColumns = ["id"],
                                                                childColumns = ["car"],
                                                                onDelete = ForeignKey.SET_NULL)])
data class Interventi (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val description:String,
    val hours:String,
    val dateStart:String,
    val dateFinish:String,
    val car:Long?
)