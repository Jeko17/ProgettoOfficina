package com.android.officina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ClientiViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clienti_view)

        findViewById<Button>(R.id.add_customers).setOnClickListener {
            this.startActivity(Intent(this, AddClienteActivity::class.java))
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.list_fragment, ClienteFragment.newInstance())
            .commit()
    }

    override fun onResume() {
        super.onResume()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.list_fragment, ClienteFragment.newInstance())
            .commit()
    }
}