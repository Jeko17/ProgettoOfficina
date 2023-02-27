package com.android.officina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.android.officina.entities.Cliente
import com.android.officina.persistence.sql.ClienteDatabase
import java.util.concurrent.Executors

class AddClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cliente)

        findViewById<Button>(R.id.add_customers).setOnClickListener{
            Executors.newSingleThreadExecutor().execute{
                val name = findViewById<TextView>(R.id.edit_name)
                val surname = findViewById<TextView>(R.id.edit_surname)
                val email = findViewById<TextView>(R.id.edit_email)

                val data = Cliente(0,name.text.toString(),surname.text.toString(),email.text.toString())

                val dao = Room.databaseBuilder(applicationContext,ClienteDatabase::class.java,ClienteDatabase.DATABASE_NAME).build().getClienteDao()
                dao.add(data)

                finish()

            }
        }

    }
}