package com.example.mapview.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapview.R
import com.example.mapview.adapter.SchoolListAdapter
import com.example.mapview.model.School


class SchoolListFragment : Fragment() {
    lateinit var listAdapter: SchoolListAdapter
    private var schoolList = arrayListOf<School.List>()
    lateinit var rvList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_school_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList = view.findViewById(R.id.rv_list)

        schoolList = arrayListOf(
            School.List("國立台灣大學", "1"),
            School.List("國立台灣科技大學", "2"),
            School.List("國立師範大學", "3"),
            School.List("世新大學", "4"),
            School.List("中國科技大學", "5"),
            School.List("臺北醫學大學", "6"),
            School.List("淡江大學","7")
        )

        listAdapter = SchoolListAdapter()
        listAdapter.list = schoolList

        rvList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }
}