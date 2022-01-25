package com.example.mapview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mapview.R
import com.example.mapview.model.School

class SchoolMapAdapter : RecyclerView.Adapter<SchoolMapAdapter.SchoolMapViewHolder>() {
    var list: ArrayList<School.Map> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolMapViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_school, parent, false)
        //viewpager問題 Pages must fill the whole ViewPager2 (use match_parent)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        return SchoolMapViewHolder(view)
    }

    override fun onBindViewHolder(holderSchool: SchoolMapViewHolder, position: Int) {
        holderSchool.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class SchoolMapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val no = itemView.findViewById<TextView>(R.id.txt_no)
        val name = itemView.findViewById<TextView>(R.id.txt_name)

        fun bind(map: School.Map) {
            no.text = "No."+map.no
            name.text = map.name
        }
    }
}