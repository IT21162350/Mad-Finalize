package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.login.R

class HansaliLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hansali_login)

        val btnLogin: Button = findViewById(R.id.loginButton);


        val btnRegister: Button = findViewById(R.id.registerButton);

        btnRegister.setOnClickListener() {
            val reg = Intent(this, RegJobSeeker::class.java)
            startActivity(reg)
        }
    }
}