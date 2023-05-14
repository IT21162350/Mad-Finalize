package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.login.R
import com.example.login.models.GuiderModel
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class GuiderProfile : AppCompatActivity() {

    private lateinit var tvGLname: TextView
    private lateinit var tvIname: TextView
    private lateinit var tvGaddress: TextView
    private lateinit var tvGTitle: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guider_profile)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog (
                intent.getStringExtra("guiderId").toString(),
                intent.getStringExtra("guiderInitname").toString()
                    )
        }

        btnDelete.setOnClickListener {
            deleteGuider(
                intent.getStringExtra("guiderId").toString()
            )
        }
    }
    private fun deleteGuider(
        Id: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("CareerGuider").child(Id)
        Log.d("TAG", Id)
        val mTask = dbRef.removeValue()
        mTask.addOnSuccessListener {
            Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_LONG).show()
            val intent = Intent(this, GuiderList::class.java)
            startActivity(intent)
        }
            .addOnFailureListener {
                error ->
                Toast.makeText(this, "Error in Deleting Data", Toast.LENGTH_LONG).show()
            }
    }

    private fun initView() {

        tvGLname = findViewById(R.id.cgLname)
        tvIname = findViewById(R.id.cdInitName)
        tvGaddress = findViewById(R.id.cdAddress)
        tvGTitle = findViewById(R.id.cdTitle)
        btnUpdate = findViewById(R.id.updateGuider)
        btnDelete = findViewById(R.id.DeleteGuider)
    }

    private fun setValuesToViews() {
        tvGLname.text = intent.getStringExtra("guiderLname")
        tvIname.text = intent.getStringExtra("guiderInitname")
        tvGaddress.text = intent.getStringExtra("guiderAddress")
        tvGTitle.text = intent.getStringExtra("guiderTitle")

    }

    private fun openUpdateDialog (
        guiderId: String,
        guiderInitName: String
    ) {
            val mDialog = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val mDialogView = inflater.inflate(R.layout.guider_update_layout, null)

        mDialog.setView(mDialogView)

        val etGuiderLastName = mDialogView.findViewById<EditText>(R.id.cgLname)
        val etGuiderInitName = mDialogView.findViewById<EditText>(R.id.cdInitName)
        val etGuiderAddress = mDialogView.findViewById<EditText>(R.id.cdAddress)
        val etGuiderTitle = mDialogView.findViewById<EditText>(R.id.cdTitle)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.updateGuider)

        etGuiderInitName.setText(intent.getStringExtra("guiderInitname").toString())
        etGuiderLastName.setText(intent.getStringExtra("guiderLname").toString())
        etGuiderAddress.setText(intent.getStringExtra("guiderAddress").toString())
        etGuiderTitle.setText(intent.getStringExtra("guiderTitle").toString())

        mDialog.setTitle("Update Guider Data")
        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateGuiderData (
                guiderId,
                etGuiderLastName.text.toString(),
                etGuiderInitName.text.toString(),
                etGuiderAddress.text.toString(),
                etGuiderTitle.text.toString()
                    )

            Toast.makeText(applicationContext, "Guider Data Updated", Toast.LENGTH_LONG).show()

            tvGLname.text = etGuiderLastName.text.toString()
            tvIname.text = etGuiderInitName.text.toString()
            tvGaddress.text = etGuiderAddress.text.toString()
            tvGTitle.text = etGuiderTitle.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateGuiderData (
        id: String,
        lName: String,
        initName: String,
        address: String,
        title: String
    )
    {
        val dbRef = FirebaseDatabase.getInstance().getReference("CareerGuider").child(id)
        val GuiderInfo = GuiderModel(id, lName, initName, address, title)
        dbRef.setValue(GuiderInfo)
    }
}