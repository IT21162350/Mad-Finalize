package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.login.HansaliAdission
import com.example.login.HansaliLogin
import com.example.login.R
import com.example.login.RegJobSeeker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Switch Activities
        val button: Button = findViewById(R.id.login)
        val hansali: Button = findViewById(R.id.cancel)


        //C Dashboard
        button.setOnClickListener {
            // Create an Intent to launch the second activity
            val janith = Intent(this, c_dashboard::class.java)
            startActivity(janith)
        }


        hansali.setOnClickListener() {
            val profile = Intent(this, jobSeekerProfile::class.java)
            startActivity(profile)
        }

    }
}