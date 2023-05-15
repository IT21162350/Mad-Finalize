package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signup: Button = findViewById(R.id.signin)
        btnLogin = findViewById(R.id.login)
        etEmail = findViewById(R.id.log_email)
        etPassword = findViewById(R.id.log_password)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val email: String = etEmail.text.toString()
            val password: String = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val mainDashboard = Intent(this, mainDashbvord::class.java)
                            startActivity(mainDashboard)
                            finish() // Optional: Close the login activity to prevent going back to it with the back button
                        } else {
                            Toast.makeText(applicationContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }

        signup.setOnClickListener{
            val signIn = Intent(this, SignIn::class.java)
            startActivity(signIn)
        }
    }
}
