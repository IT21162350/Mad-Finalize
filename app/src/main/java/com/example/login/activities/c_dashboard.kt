package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.login.R

class c_dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdashboard);

        // Object for buttons
        val home: ImageButton = findViewById(R.id.btnHome);
        val addJob: ImageButton = findViewById(R.id.btnAddJob);
        val btnJobList: ImageButton = findViewById(R.id.btnJobList);

        //home
        home.setOnClickListener(){
            val homeIntent = Intent(this, Career_Guide_Dashboard::class.java);
            startActivity(homeIntent)
        }

        //addJob
        addJob.setOnClickListener(){
            val addJobIntent = Intent(this, add_job::class.java);
            startActivity(addJobIntent);
        }

        //Job List
        btnJobList.setOnClickListener(){
            val jobListIntent = Intent(this, jobList::class.java);
            startActivity(jobListIntent);
        }

    }
}