package com.example.login.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.adepters.JobAdapter
import com.example.login.models.jobModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class jobList : AppCompatActivity() {

    private lateinit var JobRecyclerView: RecyclerView;
    private lateinit var tvLoading: TextView
    private lateinit var jobList: ArrayList<jobModel>
    private lateinit var dbRef: DatabaseReference

    private lateinit var btnJLback: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        //get count
        val databaseReference = FirebaseDatabase.getInstance().getReference("your_node")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val recordCount = dataSnapshot.childrenCount
                // Use the recordCount as needed
                Log.d(TAG, "Record count: $recordCount")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
            }
        })


        JobRecyclerView = findViewById(R.id.jobListView);
        JobRecyclerView.layoutManager = LinearLayoutManager(this);
        JobRecyclerView.setHasFixedSize(true);
        tvLoading = findViewById(R.id.tvLoading);
        btnJLback = findViewById(R.id.btnJLBack);

        jobList = arrayListOf<jobModel>();

        getJobData()

        btnJLback.setOnClickListener(){
            val backDashbord = Intent(this, c_dashboard::class.java);
            startActivity(backDashbord);
        }

    }

    private fun getJobData(){
        JobRecyclerView.visibility = View.GONE;
        tvLoading.visibility =  View.VISIBLE;

        dbRef = FirebaseDatabase.getInstance().getReference("Jobs");

        dbRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                jobList.clear();
                if (snapshot.exists()){
                    for (jobSnap in snapshot.children){
                        val jobData = jobSnap.getValue(jobModel::class.java)
                        jobList.add(jobData!!)
                    }
                    val jAdapter = JobAdapter(jobList);
                    JobRecyclerView.adapter =jAdapter;
                    JobRecyclerView.visibility = View.VISIBLE;
                    tvLoading.visibility = View.GONE;

                    jAdapter.setOnJobClickListner(object: JobAdapter.onJobClickListner{
                        override fun jobClick(position: Int) {
                            val intent = Intent(this@jobList, oneJobDetail::class.java)
                            //Extras
                            intent.putExtra("jId", jobList[position].jobId);
                            intent.putExtra("cName", jobList[position].c_name);
                            intent.putExtra("jPos", jobList[position].c_position);
                            intent.putExtra("jEdu", jobList[position].c_eduReq);
                            intent.putExtra("jWork", jobList[position].c_expReq);
                            intent.putExtra("jContact", jobList[position].c_contact);
                            startActivity(intent)

                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }
}