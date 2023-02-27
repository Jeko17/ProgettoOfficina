package com.android.officina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InterventiViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interventi_view)

        findViewById<Button>(R.id.add_interventi).setOnClickListener {
            this.startActivity(Intent(this, AddInterventiActivity::class.java))
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.list_fragment_interventi, InterventiFragment.newInstance())
            .commit()
    }

    override fun onResume() {
        super.onResume()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.list_fragment_interventi, InterventiFragment.newInstance())
            .commit()
    }
}