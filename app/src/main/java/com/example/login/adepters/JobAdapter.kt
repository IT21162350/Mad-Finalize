package com.example.login.adepters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.models.jobModel
import org.w3c.dom.Text

class JobAdapter (private val jobList: ArrayList<jobModel>):
    RecyclerView.Adapter<JobAdapter.ViewHolder>(){

    private lateinit var jListner: onJobClickListner
    interface onJobClickListner{
        fun jobClick(position: Int)
    }
    fun setOnJobClickListner(clickListner: onJobClickListner) {
        jListner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobAdapter.ViewHolder {
        val item_c_Name = LayoutInflater.from(parent.context).inflate(R.layout.job_list_layout, parent, false);
        return ViewHolder(item_c_Name, jListner);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentJob = jobList[position]
        holder.tv_Name.text = currentJob.c_name;
        holder.tv_Posision.text = currentJob.c_position;
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    class ViewHolder(JobView: View, clickListner: onJobClickListner): RecyclerView.ViewHolder(JobView) {
        val tv_Name: TextView = itemView.findViewById(R.id.c_name);
        val tv_Posision: TextView = itemView.findViewById(R.id.c_pos);

        init {
            itemView.setOnClickListener{
                clickListner.jobClick(adapterPosition)
            }
        }
    }

}