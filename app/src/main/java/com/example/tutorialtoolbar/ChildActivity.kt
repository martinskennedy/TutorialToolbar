package com.example.tutorialtoolbar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class ChildActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}