package com.example.mapview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mapview.R
import com.example.mapview.model.SchoolFilter

class FilterAdapter(
    private val sortClick: (SchoolFilter) -> Unit,
    private val filterClick: (SchoolFilter) -> Unit,
    private val otherClick: (SchoolFilter) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SORT = 0
        private const val TYPE_FILTER = 1
        private const val TYPE_OTHER = 2
    }

    var school: ArrayList<SchoolFilter> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
        return when (viewType) {
            TYPE_SORT -> {
                SortViewHolder(view)
            }
            TYPE_FILTER -> {
                FilterViewHolder(view)
            }
            TYPE_OTHER -> {
                OtherViewHolder(view)
            }
            else -> {
                throw IllegalAccessException()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = school.size


    class SortViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_filter = itemView.findViewById<TextView>(R.id.txt_filter)
        fun bind(filter:SchoolFilter){}

    }

    class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_filter = itemView.findViewById<TextView>(R.id.txt_filter)
        fun bind(filter:SchoolFilter){}

    }

    class OtherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_filter = itemView.findViewById<TextView>(R.id.txt_filter)
        fun bind(filter:SchoolFilter){}

    }
}