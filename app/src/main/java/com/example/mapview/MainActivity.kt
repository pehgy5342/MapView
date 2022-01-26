package com.example.mapview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.mapview.adapter.FilterAdapter
import com.example.mapview.fragment.SchoolListFragment
import com.example.mapview.fragment.SchoolMapFragment

class MainActivity : AppCompatActivity() {
    private val schoolSort: Array<String> get() = resources.getStringArray(R.array.schoolSort)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var isMap = false
        val rv_filter = findViewById<RecyclerView>(R.id.rv_filter)

        val txt_right = findViewById<TextView>(R.id.txt_right)
        txt_right.text = "地圖"
        txt_right.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.map, 0, 0)

        supportFragmentManager.inTransaction {
            add(R.id.fragment_container, SchoolListFragment())
            addToBackStack(null)
            setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        txt_right.setOnClickListener {
            if (!isMap) {
                isMap = true
                txt_right.text = "列表"
                txt_right.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.list, 0, 0)

                supportFragmentManager.inTransaction {
                    add(R.id.fragment_container, SchoolMapFragment())
                    addToBackStack(null)
                    setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                }
            } else {
                isMap = false
                txt_right.text = "地圖"
                txt_right.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.map, 0, 0)

                supportFragmentManager.popBackStackImmediate()
            }
        }
        val filterAdapter = FilterAdapter(
            sortClick = {
                AlertDialog.Builder(this)
                    .setTitle("請選擇排序")

            },
            filterClick = {},
            otherClick = {}
        )
        rv_filter.apply {

        }

    }
}