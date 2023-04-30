package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Switch Activities
        val button: Button = findViewById(R.id.login)

        //C Dashboard
        button.setOnClickListener {
            // Create an Intent to launch the second activity
            val intent = Intent(this, c_dashboard::class.java)
            startActivity(intent)
        }
    }
}