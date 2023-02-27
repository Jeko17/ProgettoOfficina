package com.android.officina.persistence.sql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.officina.entities.Cliente
import com.android.officina.entities.Interventi
import com.android.officina.entities.InterventiInfo
import com.android.officina.persistence.dao.InterventiDao

@Dao
interface SqlInterventiDao:InterventiDao {

    @Query("SELECT * FROM interventions")
    override fun getAll(): List<Interventi>

    @Query("SELECT * FROM interventions WHERE id = :acronym")
    fun infoInterventi(acronym: Long): Interventi

    @Query("Select i.id, i.description, i.hours, i.dateStart, i.dateFinish, ifnull(c.targa,'La macchina non presente o cancellata dal database') as targa ,\n" +
            "cu.surname || ' ' || cu.name as cliente\n" +
            "from interventions i \n" +
            "left join cars c on c.id=i.car\n" +
            "left join customers cu on cu.id=c.customer\n"+
            "WHERE i.id = :acronym")
    fun getInfoInterventi(acronym: Long): InterventiInfo

    @Insert
    override fun add(interventi: Interventi)

    @Delete
    override fun delete(interventi: Interventi)

}