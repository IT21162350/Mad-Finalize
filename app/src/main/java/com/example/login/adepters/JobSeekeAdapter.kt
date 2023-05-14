package com.example.login.adepters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.models.JobSeekerModel

class JobSeekeAdapter (private val jobSeekerList: ArrayList<JobSeekerModel>):
    RecyclerView.Adapter<JobSeekeAdapter.ViewHolder> () {

    private lateinit var mListner: onItemClickListner
    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner) {
        mListner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_seeker_list, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curruntJobSeeker =jobSeekerList[position]
        holder.tvJobSeekerName.text = curruntJobSeeker.f_name
    }

    override fun getItemCount(): Int {
       return jobSeekerList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView) {
        val tvJobSeekerName: TextView = itemView.findViewById(R.id.tvJobSeekerName)
        init {
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }

    }

}