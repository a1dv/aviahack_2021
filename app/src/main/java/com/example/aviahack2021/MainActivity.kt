package com.example.aviahack2021

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun CheckGM(view: View) {

        val textr = EditTee.text.toString()

        val mapIntent = Intent(this, MapsActivity::class.java)

        mapIntent.putExtra(MapsActivity.KEY, textr)
        startActivity(mapIntent)
    }
}


