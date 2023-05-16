package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login.R.*
import com.example.login.models.jobModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add_job : AppCompatActivity() {

    //Variables for Inputs
    private lateinit var companyName: EditText;
    private lateinit var position: EditText;
    private lateinit var educationReq: EditText;
    private lateinit var expReq: EditText;
    private lateinit var conEmail: EditText;

    //var for button
    private lateinit var btnaddJob: Button;
    private lateinit var btnBack: Button;

    //Variable to bdRef
    private lateinit var  dbRef: DatabaseReference;

    //patterns
    private var pattern = Regex("^\\w+([.-]?\\w+)*@gmail\\.com$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_add_job)

        companyName = findViewById(id.txtcomName);
        position = findViewById(id.txtPosition);
        educationReq = findViewById(id.txtEduReq);
        expReq = findViewById(id.txtExpReq);
        conEmail = findViewById(id.txtEmail);

        btnaddJob = findViewById(id.addJList);
        btnBack = findViewById(id.btnBack);

        dbRef = FirebaseDatabase.getInstance().getReference("Jobs");

        btnaddJob.setOnClickListener() {
            saveJobDetails();
        }
        btnBack.setOnClickListener() {
            val homeIntent = Intent(this, c_dashboard::class.java);
            startActivity(homeIntent);
        }

    }

    //Add Jop Details
    private fun saveJobDetails() {
        // getting details
        val c_name = companyName.text.toString();
        val c_position = position.text.toString();
        val c_eduReq = educationReq.text.toString();
        val c_expReq = expReq.text.toString();
        val c_email = conEmail.text.toString();

        if (c_name.isEmpty()){
            companyName.error = "Please enter your Company Name!"
        }
        if (c_position.isEmpty()){
            position.error = "Please Enter Position!"
        }
        if (c_eduReq.isEmpty()){
            educationReq.error = "Please Add Education Requirements!"
        }
        if (c_expReq.isEmpty()){
            expReq.error = "Please Add Experence Requirements"
        }
        if (c_email.isEmpty()) {
            conEmail.error = "Please add an Email to Contact"
        }
        if (!pattern.matches(c_email)) {
            conEmail.error = "Invalid Email "
        }
        else {
            //Key
            val jobId = dbRef.push().key

            val job = jobModel(jobId, c_name, c_position, c_eduReq, c_expReq, c_email);

            if (jobId != null) {
                dbRef.child(jobId).setValue(job)
                    .addOnCompleteListener() {
                        Toast.makeText(this, "Job Added successfully!", Toast.LENGTH_LONG).show();

                        //
                        companyName.text.clear();
                        position.text.clear();
                        educationReq.text.clear();
                        expReq.text.clear();
                        conEmail.text.clear();
                    }
                    .addOnFailureListener() { err ->
                        Toast.makeText(this, "Error ${err.message}!", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}