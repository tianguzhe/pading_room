package com.rongyi.pagingdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rongyi.pagingdemo.dao.Student

class MyPagingAdapter() : PagedListAdapter<Student, MyPagingAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.studentNumber == newItem.studentNumber
    }
}) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textview.text = getItem(position)?.studentNumber.toString()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textview = view.findViewById<TextView>(R.id.textView)
    }

}