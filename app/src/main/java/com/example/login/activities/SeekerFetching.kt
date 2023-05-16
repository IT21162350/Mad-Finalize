package com.example.login.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.adepters.JobSeekeAdapter
import com.example.login.models.JobSeekerModel
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Job

class SeekerFetching : AppCompatActivity() {

    private lateinit var jobSeekerRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var JobSeekerList: ArrayList<JobSeekerModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeker_fetching)

        jobSeekerRecyclerView = findViewById(R.id.rvJobSeekers)
        jobSeekerRecyclerView.layoutManager = LinearLayoutManager(this)
        jobSeekerRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        JobSeekerList = arrayListOf<JobSeekerModel>()
        getJobSeekersData()

        //Count
        val collectionReference = FirebaseFirestore.getInstance().collection("your_collection")

        collectionReference.get()
            .addOnSuccessListener { querySnapshot ->
                val recordCount = querySnapshot.size()
                //
                Log.d(ContentValues.TAG, "Record count: $recordCount")
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    private fun getJobSeekersData() {
        jobSeekerRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("JobSeeker")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               JobSeekerList.clear()
                if (snapshot.exists()){
                    for (seekerSnap in snapshot.children){
                        val SeekerData = seekerSnap.getValue(JobSeekerModel::class.java)
                        JobSeekerList.add(SeekerData!!)
                    }
                    val sAdapter = JobSeekeAdapter(JobSeekerList)
                    jobSeekerRecyclerView.adapter = sAdapter

                    sAdapter.setOnItemClickListner(object: JobSeekeAdapter.onItemClickListner {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@SeekerFetching, JobSeekerProfile::class.java)
                            //put extra data
                            intent.putExtra("jsId", JobSeekerList[position].jsId)
                            intent.putExtra("fname", JobSeekerList[position].f_name)
                            intent.putExtra("lname", JobSeekerList[position].l_name)
                            intent.putExtra("cnum", JobSeekerList[position].c_num)
                            intent.putExtra("email", JobSeekerList[position].Email)
                            intent.putExtra("address", JobSeekerList[position].Address)
                            startActivity(intent)
                        }

                    })

                    jobSeekerRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}