package com.example.login.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.adepters.GuiderAdapter
import com.example.login.models.GuiderModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

class GuiderList : AppCompatActivity() {
    private lateinit var guiderRecyclerView: RecyclerView
    private lateinit var tvLoadindData:TextView
    private lateinit var guiderList: ArrayList<GuiderModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guider_list)

        guiderRecyclerView = findViewById(R.id.guider_recyclerView)
        guiderRecyclerView.layoutManager =LinearLayoutManager(this)
        guiderRecyclerView.setHasFixedSize(true)
        tvLoadindData = findViewById(R.id.tvLoadingDataGuider)

        guiderList = arrayListOf<GuiderModel>()
        getGuiderData()

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
    private fun getGuiderData() {
        guiderRecyclerView.visibility = View.GONE
        tvLoadindData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("CareerGuider")
        dbRef.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                guiderList.clear()
                if(snapshot.exists()) {
                    for (guiderSnap in snapshot.children) {
                        val guiderData = guiderSnap.getValue(GuiderModel::class.java)
                        guiderList.add(guiderData!!)
                    }
                    val mAdapter = GuiderAdapter(guiderList)
                    guiderRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListner(object: GuiderAdapter.onItemClickListner {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@GuiderList, GuiderProfile::class.java)

                            //Put extra data
                            intent.putExtra("guiderId", guiderList[position].guiderId)
                            intent.putExtra("GuiderFname", guiderList[position].GuiderFname)
                            intent.putExtra("guiderLname", guiderList[position].GuiderLname)
                            intent.putExtra("guiderInitname", guiderList[position].GuiderInitName)
                            intent.putExtra("guiderAddress", guiderList[position].GuiderAddress)
                            intent.putExtra("guiderTitle", guiderList[position].GuiderTitle)
                            startActivity(intent)
                        }
                    })

                    guiderRecyclerView.visibility = View.VISIBLE
                    tvLoadindData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}