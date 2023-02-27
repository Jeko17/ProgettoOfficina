package com.android.officina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AutoViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_view)

        findViewById<Button>(R.id.add_cars).setOnClickListener {
            this.startActivity(Intent(this, AddAutoActivity::class.java))
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.list_fragment_cars, AutoFragment.newInstance())
            .commit()
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.list_fragment_cars, AutoFragment.newInstance())
            .commit()
    }

}