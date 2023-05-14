package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.login.R

class Notification_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_page)

        val btnNext: Button
        btnNext = findViewById(R.id.btnGuiderList)

        btnNext.setOnClickListener() {
            val intent = Intent(this, GuiderList::class.java)
            startActivity(intent)
        }
    }
}