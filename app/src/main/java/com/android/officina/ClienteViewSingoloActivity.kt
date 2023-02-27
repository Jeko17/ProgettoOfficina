package com.android.officina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.android.officina.entities.Cliente
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class ClienteViewSingoloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_view_singolo)

        Executors.newSingleThreadExecutor().execute {
            val dao = Room.databaseBuilder(applicationContext,ClienteDatabase::class.java,ClienteDatabase.DATABASE_NAME)
                .build().getClienteDao()
            val cliente: Cliente = dao.infoCustomers(intent.getLongExtra("id",0))
            findViewById<TextView>(R.id.info_name).text = cliente.name
            findViewById<TextView>(R.id.info_surname).text = cliente.surname
            findViewById<TextView>(R.id.info_email).text = cliente.email
        }
    }
}