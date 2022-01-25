package com.example.mapview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.inTransaction {
            add(R.id.fragment_container, MapFragment())
            addToBackStack(null)
            setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
    }
}