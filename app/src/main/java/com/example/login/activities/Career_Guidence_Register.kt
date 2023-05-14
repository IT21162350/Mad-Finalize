package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login.R
import com.example.login.models.GuiderModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Career_Guidence_Register : AppCompatActivity() {

    private lateinit var etcgFname: EditText
    private lateinit var etcgLname: EditText
    private lateinit var etcdInitName: EditText
    private lateinit var etcdAddress: EditText
    private lateinit var etcdTitle: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    //Patterns
    private var validTitlePattern = "^(Mr|Mrs|Miss)$".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_career_guidence_register)

        etcgFname = findViewById(R.id.cgFname)
        etcgLname =  findViewById(R.id.cgLname)
        etcdInitName = findViewById(R.id.cdInitName)
        etcdAddress =  findViewById(R.id.cdAddress)
        etcdTitle =  findViewById(R.id.cdTitle)
        btnSaveData = findViewById(R.id.RegGuider)

        dbRef = FirebaseDatabase.getInstance().getReference("CareerGuider")

        btnSaveData.setOnClickListener() {
            saveCareerGuider()
        }
    }
    private fun saveCareerGuider() {

        //getting data from layout
        val fname = etcgFname.text.toString()
        val lname =  etcgLname.text.toString()
        val initName = etcdInitName.text.toString()
        val address = etcdAddress.text.toString()
        val title = etcdTitle.text.toString()

        if (fname.isEmpty()) {
            etcgFname.error = "Please add the first name"
        }

        if (lname.isEmpty()) {
            etcgLname.error = "Please add last name"
        }
        if (initName.isEmpty()) {
            etcdInitName.error = "Please add an Initial name"
        }
        if (address.isEmpty()) {
            etcdAddress.error = "Please add an address"
        }
        if (title.isEmpty()) {
            etcdTitle.error = "Please add a Title"
        }
        if (!validTitlePattern.matches(title)) {
            etcdTitle.error = "Invalid Title"
        }
        else {
            val guiderId = dbRef.push().key!!
            val Guider = GuiderModel(guiderId, fname, lname, initName, address, title)
            dbRef.child(guiderId).setValue(Guider)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data Inserted Successfully!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, Notification_Page::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    err ->
                    Toast.makeText(this, "Error with Data Inserting!", Toast.LENGTH_LONG).show()
                }
        }
    }
}