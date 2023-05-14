package com.example.login.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.login.R
import org.w3c.dom.Text

class JobSeekerProfile : AppCompatActivity() {

    private lateinit var fname: TextView
    private lateinit var lname: TextView
    private lateinit var cnum: TextView
    private lateinit var email: TextView
    private lateinit var address: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_seeker_profile)
        initView()
        setValuesToViews()
    }

    private fun initView() {
        fname = findViewById(R.id.jsFname)
        lname = findViewById(R.id.jsLname)
        cnum = findViewById(R.id.jsMnumber)
        email =  findViewById(R.id.jsEmail)
        address = findViewById(R.id.jsAddress)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete =  findViewById(R.id.btnJsDel)
    }

    private fun setValuesToViews() {
        fname.text = intent.getStringExtra("fname")
        lname.text = intent.getStringExtra("lname")
        cnum.text = intent.getStringExtra("cnum")
        email.text = intent.getStringExtra("email")
        address.text =  intent.getStringExtra("address")
    }
}