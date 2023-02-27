package com.android.officina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import com.android.officina.entities.Auto
import com.android.officina.entities.AutoInfo
import com.android.officina.entities.Cliente
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class AutoViewSingoloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_view_singolo)

        Executors.newSingleThreadExecutor().execute {
            val dao = Room.databaseBuilder(applicationContext,
                ClienteDatabase::class.java,
                ClienteDatabase.DATABASE_NAME)
                .build().getAutoDao()
            val auto: AutoInfo = dao.getInfoCarsTest(intent.getLongExtra("id",0))
            findViewById<TextView>(R.id.info_targa).text = auto.targa
            findViewById<TextView>(R.id.info_telaio).text = auto.telaio
            findViewById<TextView>(R.id.info_model).text = auto.model
            findViewById<TextView>(R.id.info_customers).text = auto.name_complete
        }
    }
}