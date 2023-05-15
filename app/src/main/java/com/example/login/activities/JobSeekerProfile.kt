package com.example.login.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.login.R
import com.example.login.models.JobSeekerModel
import com.google.firebase.database.FirebaseDatabase



class JobSeekerProfile : AppCompatActivity() {

    private lateinit var jsId: TextView
    private lateinit var fname: TextView
    private lateinit var lname: TextView
    private lateinit var cnum: TextView
    private lateinit var email: TextView
    private lateinit var address: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_seeker_profile)
        initView()
        setValuesToViews()

        btnDelete.setOnClickListener() {
            deleteRecord(
                intent.getStringExtra("jsId").toString()
            )
        }

        btnUpdate.setOnClickListener() {
            openUpdateDialog(
                intent.getStringExtra("jsId").toString(),
                intent.getStringExtra("fname").toString()
            )
        }

        btnEmail.setOnClickListener() {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com")) // Set the recipient email address
            intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject") // Set the email subject

            // Set explicit package name for the Gmail app
            intent.setPackage("com.google.android.gm")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // If the Gmail app is not available, fall back to other email apps or show an error message
                val emailAppChooser = Intent.createChooser(intent, "Open with")
                if (emailAppChooser.resolveActivity(packageManager) != null) {
                    startActivity(emailAppChooser)
                } else {
                    Toast.makeText(applicationContext, "No email app found.", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun initView() {
        jsId = findViewById(R.id.jsId)
        fname = findViewById(R.id.jsFname)
        lname = findViewById(R.id.jsLname)
        cnum = findViewById(R.id.jsMnumber)
        email =  findViewById(R.id.jsEmail)
        address = findViewById(R.id.jsAddress)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete =  findViewById(R.id.btnJsDel)
        btnEmail = findViewById(R.id.btnEmail)
    }

    private fun setValuesToViews() {
        jsId.text = intent.getStringExtra("jsId")
        fname.text = intent.getStringExtra("fname")
        lname.text = intent.getStringExtra("lname")
        cnum.text = intent.getStringExtra("cnum")
        email.text = intent.getStringExtra("email")
        address.text =  intent.getStringExtra("address")
    }

    private fun deleteRecord(id: String?) {
        val dbRef =  FirebaseDatabase.getInstance().getReference("JobSeeker").child(id!!)
        val mTask = dbRef.removeValue()
        Log.d("TAG", id);

        //If delete success
        mTask.addOnSuccessListener {
            Toast.makeText(this, "Profile Deleted", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SeekerFetching::class.java)
            startActivity(intent)

        } //If Delete failed
            .addOnFailureListener {
                error ->
                Toast.makeText(this, "Error in Profile Deleting", Toast.LENGTH_SHORT).show()
            }
    }

    private fun openUpdateDialog( jsId: String, jsfName: String) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_profile, null)

        mDialog.setView(mDialogView)

        val etjsFname = mDialogView.findViewById<EditText>(R.id.jsFname)
        val etjsLname = mDialogView.findViewById<EditText>(R.id.jsLname)
        val etjsCnum = mDialogView.findViewById<EditText>(R.id.jsMnumber)
        val etjsEmail = mDialogView.findViewById<EditText>(R.id.jsEmail)
        val etjsAddress = mDialogView.findViewById<EditText>(R.id.jsAddress)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etjsFname.setText(intent.getStringExtra("fname").toString())
        etjsLname.setText(intent.getStringExtra("lname").toString())
        etjsCnum.setText(intent.getStringExtra("cnum").toString())
        etjsEmail.setText(intent.getStringExtra("email").toString())
        etjsAddress.setText(intent.getStringExtra("address").toString())

        mDialog.setTitle("Update Profile")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener(){
            updateJobSeekerData (
                jsId,
                etjsFname.text.toString(),
                etjsLname.text.toString(),
                etjsCnum.text.toString(),
                etjsEmail.text.toString(),
                etjsAddress.text.toString()
            )
            Toast.makeText(applicationContext, "Profile Updated", Toast.LENGTH_LONG).show()

            fname.text = etjsFname.text.toString()
            lname.text = etjsLname.text.toString()
            cnum.text = etjsCnum.text.toString()
            email.text = etjsEmail.text.toString()
            address.text =  etjsAddress.text.toString()

            alertDialog.dismiss()
        }
    }

    //Update Method
    private fun updateJobSeekerData (
        id: String,
        fname: String,
        lname: String,
        cnum: String,
        email: String,
        address: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("JobSeeker").child(id)
        val jsInfo = JobSeekerModel(id, fname, lname, cnum, email, address)
        dbRef.setValue(jsInfo)
    }
}