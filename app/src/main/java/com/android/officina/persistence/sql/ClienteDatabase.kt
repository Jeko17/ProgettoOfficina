package com.android.officina.persistence.sql

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.officina.entities.Auto
import com.android.officina.entities.Cliente
import com.android.officina.entities.Interventi

@Database(
    version = ClienteDatabase.VERSIONE,
    entities = [Cliente::class, Auto::class, Interventi::class])
abstract class ClienteDatabase: RoomDatabase() {
    companion object{
        const val VERSIONE = 2
        const val DATABASE_NAME="officina"
    }

    abstract fun getClienteDao(): SqlClienteDao
    abstract fun getAutoDao(): SqlAutoDao
    abstract fun getInterventiDao(): SqlInterventiDao
}