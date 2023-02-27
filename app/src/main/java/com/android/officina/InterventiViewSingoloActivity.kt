package com.android.officina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import com.android.officina.entities.AutoInfo
import com.android.officina.entities.Interventi
import com.android.officina.entities.InterventiInfo
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class InterventiViewSingoloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interventi_view_singolo)

        Executors.newSingleThreadExecutor().execute {
            val dao = Room.databaseBuilder(applicationContext,
                ClienteDatabase::class.java,
                ClienteDatabase.DATABASE_NAME)
                .build().getInterventiDao()
            val intervento: InterventiInfo = dao.getInfoInterventi(intent.getLongExtra("id",0))

            findViewById<TextView>(R.id.info_targa).text = intervento.targa
            findViewById<TextView>(R.id.info_datastart).text = intervento.dateStart
            findViewById<TextView>(R.id.info_datafinish).text = intervento.dateFinish
            findViewById<TextView>(R.id.info_hours).text = intervento.hours
            findViewById<TextView>(R.id.info_description).text = intervento.description
            findViewById<TextView>(R.id.info_name).text = intervento.cliente
        }
    }
}