package com.android.officina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.room.Room
import com.android.officina.entities.Auto
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class AddAutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_auto)

        findViewById<Spinner>(R.id.select_customers).apply {
            Executors.newSingleThreadExecutor().execute{
                val data = Room
                    .databaseBuilder(applicationContext,ClienteDatabase::class.java,ClienteDatabase.DATABASE_NAME)
                    .build()
                    .getClienteDao()
                    .getAll()
                    .map {  c -> c.id.toString() + ". " + c.name + " " + c.surname }
                adapter = ArrayAdapter(
                    this@AddAutoActivity,
                    android.R.layout.simple_list_item_1,
                    data
                )
            }
        }

        findViewById<Button>(R.id.button_add_car).setOnClickListener{
            Executors.newSingleThreadExecutor().execute {
                val targa = findViewById<TextView>(R.id.edit_targa)
                val telaio = findViewById<TextView>(R.id.edit_telaio)
                val model = findViewById<TextView>(R.id.edit_modello)
                val cliente = findViewById<Spinner>(R.id.select_customers).selectedItem.toString()
                val id_cliente = Integer.parseInt(cliente.substring(0,cliente.indexOf("."))).toLong()
                val data = Auto(0,telaio.text.toString(),model.text.toString(),targa.text.toString(), id_cliente)

                val dao = Room.databaseBuilder(applicationContext,ClienteDatabase::class.java,ClienteDatabase.DATABASE_NAME)
                    .build()
                    .getAutoDao()
                    dao.add(data)
                finish()
            }
        }
    }
}