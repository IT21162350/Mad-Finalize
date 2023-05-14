package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.login.R

class mainDashbvord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashbvord)

        val btnjobList = findViewById<Button>(R.id.jobList)
        val btnJobSeeker = findViewById<Button>(R.id.JobSeeker)
        val btnCGdasshbooard = findViewById<Button>(R.id.CGDashboard)

        btnjobList.setOnClickListener() {
            val jobList =  Intent(this, c_dashboard::class.java)
            startActivity(jobList)
        }

        btnJobSeeker.setOnClickListener() {
            val JobSeeker = Intent(this, RegJobSeeker::class.java)
            startActivity(JobSeeker)
        }

        btnCGdasshbooard.setOnClickListener() {
            val CGDboard = Intent(this, Career_Guidence_Register::class.java)
            startActivity(CGDboard)
        }
    }
}