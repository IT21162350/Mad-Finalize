package com.example.login.adepters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.models.GuiderModel

class GuiderAdapter(private val guiderList: ArrayList<GuiderModel>):
    RecyclerView.Adapter<GuiderAdapter.ViewHolder>() {

    private lateinit var mListner: onItemClickListner

    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner) {
        mListner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.guider_list_layout, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curruntGuider = guiderList[position]
        holder.tvGuiderName.text =  curruntGuider.GuiderInitName

    }
    override fun getItemCount(): Int {
       return guiderList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView) {
        val tvGuiderName: TextView =  itemView.findViewById(R.id.tvGuiderName)
        init {
            itemView.setOnClickListener {
                clickListner.onItemClick(adapterPosition)
            }
        }
    }
}