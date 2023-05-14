package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login.R
import com.example.login.models.JobSeekerModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegJobSeeker : AppCompatActivity() {

    private lateinit var etJsSeekerFname: EditText;
    private lateinit var etJsSeekerLname: EditText;
    private lateinit var etJsMnumber: EditText;
    private lateinit var etJsEmail: EditText;
    private lateinit var etJsAddress: EditText;
    private lateinit var etJspassword: EditText;
    private lateinit var etJsConPassword: EditText;

    private lateinit var saveData: Button;

    private lateinit var dbRef: DatabaseReference;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_job_seeker)

        etJsSeekerFname = findViewById(R.id.FirestNameJS)
        etJsSeekerLname = findViewById(R.id.LastNameJS)
        etJsMnumber = findViewById(R.id.ContactJS);
        etJsEmail = findViewById(R.id.EmailJS);
        etJsAddress = findViewById(R.id.AddressJS)
        etJspassword = findViewById(R.id.PasswordJS)
        etJsConPassword = findViewById(R.id.CnfPasswordJS)

        saveData = findViewById(R.id.saveJSData)

        dbRef = FirebaseDatabase.getInstance().getReference("JobSeeker")

        saveData.setOnClickListener {
            saveJobSeekerData()
        }
    }

    private fun saveJobSeekerData() {
        //get Values
        val jsFname = etJsSeekerFname.text.toString();
        val jsLname = etJsSeekerLname.text.toString();
        val jsNum = etJsMnumber.text.toString();
        val jsEmail = etJsEmail.text.toString();
        val jsAddress = etJsAddress.text.toString();
        val jsPassword = etJspassword.text.toString();
        val jsconPassword = etJsConPassword.text.toString();

        if (jsFname.isEmpty()){
            etJsSeekerFname.error =  "Please add First Name!"
        }
        if (jsLname.isEmpty()){
            etJsSeekerFname.error =  "Please add Last Name!"
        }
        if (jsNum.isEmpty()){
            etJsSeekerFname.error =  "Please add Contact Number!"
        }
        if (jsEmail.isEmpty()){
            etJsSeekerFname.error =  "Please add Email Address!"
        }
        if (jsAddress.isEmpty()){
            etJsSeekerFname.error =  "Please add Address!!"
        }
        if (jsPassword.isEmpty()){
            etJsSeekerFname.error =  "Please add The Password!"
        }
        if (jsconPassword.isEmpty()){
            etJsSeekerFname.error =  "Please Re enter Your Password!!"
        }
        if (jsPassword != jsconPassword){
            Toast.makeText(applicationContext, "Password is mismatching", Toast.LENGTH_SHORT).show()
        }
        else {
            val jsId = dbRef.push().key!!
            val jobSeeker = JobSeekerModel(jsFname, jsLname, jsNum, jsEmail, jsAddress, jsPassword)

            dbRef.child(jsId).setValue(jobSeeker).addOnCompleteListener {
                Toast.makeText(applicationContext, "Data Inserted Successfully", Toast.LENGTH_SHORT).show()
                val SeekerFetching = Intent(this, SeekerFetching::class.java)
                startActivity(SeekerFetching)
            }
                .addOnFailureListener { err ->
                    Toast.makeText(applicationContext, "Data Inserting Failed ${err.message}", Toast.LENGTH_SHORT).show()
                }

        }

    }
}