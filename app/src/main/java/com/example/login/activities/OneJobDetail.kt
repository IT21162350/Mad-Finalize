package com.example.login.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.login.R
import com.example.login.models.jobModel
import com.google.firebase.database.FirebaseDatabase

class oneJobDetail : AppCompatActivity() {

    private lateinit var cName: TextView;
    private lateinit var cPos: TextView;
    private lateinit var cCon: TextView;
    private lateinit var cWork: TextView;
    private lateinit var cEdu: TextView
    private lateinit var Id: TextView

    private lateinit var btnUpdate: Button;
    private lateinit var btnDelete: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_job_detail);
        initView();
        setValues();

        //Delete
        btnDelete.setOnClickListener(){
            deleteRecode(
                intent.getStringExtra("jId").toString()
            )
        }
        //Update
        btnUpdate.setOnClickListener(){
            openUpdateDialog (
                intent.getStringExtra("jId").toString(),
                intent.getStringExtra("cName").toString()
                    )
        }
    }
    private fun initView(){
        Id = findViewById(R.id.jId)
        cName = findViewById(R.id.jC_name)
        cPos = findViewById(R.id.jC_pos)
        cCon = findViewById(R.id.jC_con)
        cWork = findViewById(R.id.jC_work)
        cEdu = findViewById(R.id.jC_edu)

        btnDelete = findViewById(R.id.btnDeletJ)
        btnUpdate = findViewById(R.id.btnUpdateJ)
    }

    private fun setValues(){
        Id.text = intent.getStringExtra("jId");
        cName.text = intent.getStringExtra("cName")
        cPos.text = intent.getStringExtra("jPos")
        cCon.text = intent.getStringExtra("jContact")
        cWork.text = intent.getStringExtra("jWork")
        cEdu.text = intent.getStringExtra("jEdu")
    }

    private fun openUpdateDialog (
        jId: String,
        jcName: String
    ){
        val jDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val jDialogView = inflater.inflate(R.layout.update, null);

        jDialog.setView(jDialogView)

        val upCname = jDialogView.findViewById<EditText>(R.id.upCname);
        val upCpos = jDialogView.findViewById<EditText>(R.id.upCpos);
        val upCwork = jDialogView.findViewById<EditText>(R.id.upCedu);
        val upCedu = jDialogView.findViewById<EditText>(R.id.upCwork);
        val upCcon = jDialogView.findViewById<EditText>(R.id.upCmail);
        val upUpdateButton = jDialogView.findViewById<Button>(R.id.btnDataUpdate);

        upCname.setText(intent.getStringExtra("cName").toString())
        upCpos.setText(intent.getStringExtra("jPos").toString())
        upCwork.setText(intent.getStringExtra("jWork").toString())
        upCedu.setText(intent.getStringExtra("jEdu").toString())
        upCcon.setText(intent.getStringExtra("jContact").toString())


        jDialog.setTitle("Update Data!")

        val alertDialog = jDialog.create()
        alertDialog.show()

        upUpdateButton.setOnClickListener() {
            updateJobData(
                jId,
                upCname.text.toString(),
                upCpos.text.toString(),
                upCcon.text.toString(),
                upCwork.text.toString(),
                upCedu.text.toString()
            )
            Toast.makeText(applicationContext, "Job DATA UPDATED!", Toast.LENGTH_LONG).show()

            //Getting Updated data
            cName.text = upCname.text.toString()
            cPos.text =  upCpos.text.toString()
            cCon.text = upCcon.text.toString()
            cWork.text =  upCwork.text.toString()
            cEdu.text =  upCedu.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun deleteRecode(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Jobs").child(id)
        val jTask = dbRef.removeValue()

        jTask.addOnSuccessListener {
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, jobList::class.java).apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TOP })
        }
            .addOnFailureListener{
                    error ->
                Toast.makeText(this, "Data Delete Failed ${error.message}", Toast.LENGTH_LONG).show()
            }
    }


    private fun updateJobData(
        id: String,
        cname: String,
        cpos: String,
        ccon: String,
        cwork: String,
        cedu: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Jobs").child(id)
        val jobInfo = jobModel(id, cname, cpos, ccon, cwork, cedu)
        dbRef.setValue(jobInfo)
    }

}