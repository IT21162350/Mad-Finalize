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
            val hansali = Intent(this, c_dashboard::class.java)
            startActivity(hansali)
        }

        signup.setOnClickListener{
            val RegJobSeeker = Intent(this, RegJobSeeker::class.java)
            startActivity(RegJobSeeker)
        }

    }
}
