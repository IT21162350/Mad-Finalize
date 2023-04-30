package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class c_dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdashboard);

        // Object for buttons
        val home: ImageButton = findViewById(R.id.btnHome);
        val jobList: ImageButton = findViewById(R.id.btnJobList);
        val addJob: ImageButton = findViewById(R.id.btnAddJob);

        //home
        home.setOnClickListener(){
            val homeIntent = Intent(this, MainActivity::class.java);
            startActivity(homeIntent)
        }

        //JobList
        addJob.setOnClickListener(){
            val addJobIntent = Intent(this, add_job::class.java);
            startActivity(addJobIntent)
        }

        //Add job

    }
}