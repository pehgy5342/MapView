package com.example.mapview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MapAdapter : RecyclerView.Adapter<MapAdapter.MapViewHolder>() {
    var list: ArrayList<School> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_map, parent, false)
        //viewpager問題 Pages must fill the whole ViewPager2 (use match_parent)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        return MapViewHolder(view)
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class MapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val no = itemView.findViewById<TextView>(R.id.txt_no)
        val name = itemView.findViewById<TextView>(R.id.txt_name)

        fun bind(school: School) {
            no.text = "No."+school.no
            name.text = school.name
        }
    }
}