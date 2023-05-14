package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.login.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signup: Button = findViewById(R.id.signin)
        val login: Button = findViewById(R.id.login)

        login.setOnClickListener {
            val mainDashboard = Intent(this, mainDashbvord::class.java)
            startActivity(mainDashboard)
        }

        signup.setOnClickListener{

        }

    }
}
