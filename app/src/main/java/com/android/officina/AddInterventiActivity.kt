package com.android.officina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.room.Room
import com.android.officina.entities.Auto
import com.android.officina.entities.Cliente
import com.android.officina.entities.Interventi
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class AddInterventiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_interventi)

        findViewById<Spinner>(R.id.select_cars).apply {
            Executors.newSingleThreadExecutor().execute{
                val data = Room
                    .databaseBuilder(applicationContext,ClienteDatabase::class.java,ClienteDatabase.DATABASE_NAME)
                    .build()
                    .getAutoDao()
                    .getAll()
                    .map {  c -> c.id.toString() + ". " + c.targa }
                adapter = ArrayAdapter(
                    this@AddInterventiActivity,
                    android.R.layout.simple_list_item_1,
                    data
                )
            }
        }

        findViewById<Button>(R.id.add_interventi).setOnClickListener{
            Executors.newSingleThreadExecutor().execute {
                val datastart = findViewById<TextView>(R.id.edit_datastart)
                val datafinish = findViewById<TextView>(R.id.edit_datafinish)
                val description = findViewById<TextView>(R.id.edit_description)
                val hours = findViewById<TextView>(R.id.edit_hours)
                val auto = findViewById<Spinner>(R.id.select_cars).selectedItem.toString()
                val id_auto = Integer.parseInt(auto.substring(0,auto.indexOf("."))).toLong()
                val data = Interventi(0,description.text.toString(),hours.text.toString(),datastart.text.toString(),datafinish.text.toString(), id_auto)

                val dao = Room.databaseBuilder(applicationContext,ClienteDatabase::class.java,ClienteDatabase.DATABASE_NAME)
                    .build()
                    .getInterventiDao()
                dao.add(data)
                finish()
            }
        }
    }
}