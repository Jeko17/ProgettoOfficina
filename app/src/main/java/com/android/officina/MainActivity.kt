package com.android.officina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_list_customers).setOnClickListener {
            this.startActivity(Intent(this, ClientiViewActivity::class.java))
        }

        findViewById<Button>(R.id.button_list_cars).setOnClickListener {
            this.startActivity(Intent(this, AutoViewActivity::class.java))
        }

        findViewById<Button>(R.id.button_list_interventi).setOnClickListener {
            this.startActivity(Intent(this, InterventiViewActivity::class.java))
        }
    }
}