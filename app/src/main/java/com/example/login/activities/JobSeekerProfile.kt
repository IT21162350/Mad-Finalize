package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.login.R
import com.example.login.UpdateJobSeeker

class jobSeekerProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_seeker_profile)

        val btnUpdate: Button = findViewById(R.id.btnUpdate)

        btnUpdate.setOnClickListener() {
            val updateForm = Intent(this, UpdateJobSeeker::class.java)
            startActivity(updateForm)
        }
    }
}