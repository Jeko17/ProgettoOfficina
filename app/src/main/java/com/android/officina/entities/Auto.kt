package com.android.officina.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


// Creo la tabella auto con i dati delle auto
// con la chiave esterna customer che indica a quale cliente e' assocciata l'auto
@Entity(tableName = "cars", foreignKeys = [ForeignKey(entity = Cliente::class,
                                                        parentColumns = ["id"],
                                                        childColumns = ["customer"],
                                                        onDelete = ForeignKey.SET_NULL)])
data class Auto (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val telaio: String,
    val model: String,
    val targa:String,
    val customer:Long?
)

