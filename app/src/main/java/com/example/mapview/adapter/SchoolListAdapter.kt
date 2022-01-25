package com.example.mapview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mapview.R
import com.example.mapview.model.School

class SchoolListAdapter : RecyclerView.Adapter<SchoolListAdapter.SchoolListViewHolder>() {
    var list: ArrayList<School.List> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_school, parent, false)
        return SchoolListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchoolListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class SchoolListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val no = itemView.findViewById<TextView>(R.id.txt_no)
        val name = itemView.findViewById<TextView>(R.id.txt_name)

        fun bind(list: School.List) {
            no.text = list.no
            name.text = list.name
        }
    }
}