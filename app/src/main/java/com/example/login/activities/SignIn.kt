package com.example.login.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login.R
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    private lateinit var  etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnReg: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etEmail = findViewById(R.id.reg_mail)
        etPassword = findViewById(R.id.reg_password)
        etConfirmPassword = findViewById(R.id.com_password)
        btnReg = findViewById(R.id.btnReg)

        btnReg.setOnClickListener() {
                auth = FirebaseAuth.getInstance()
               val email: String = etEmail.text.toString()
               val password: String = etPassword.text.toString()
                val comPassword: String = etConfirmPassword.text.toString()

                if (email.isEmpty() || password.isEmpty() || comPassword.isEmpty()) {
                    Toast.makeText(applicationContext, "Fill all the required field", Toast.LENGTH_LONG).show()
                }
                else if (password != comPassword){
                    Toast.makeText(applicationContext, "Password Mismatched", Toast.LENGTH_LONG).show()
                }
                else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(applicationContext,"Authentication failed.", Toast.LENGTH_SHORT).show()
                            }
                        }
                }

        }

    }
}