package com.rongyi.pagingdemo

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rongyi.pagingdemo.dao.Student
import com.rongyi.pagingdemo.dao.StudentDao
import com.rongyi.pagingdemo.dao.StudentDataBase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var studentDao: StudentDao
    lateinit var studentDataBase: StudentDataBase
    lateinit var allStudents: LiveData<PagedList<Student>>

    private val adapter by lazy {
        MyPagingAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentDataBase = StudentDataBase.getDataBase(this)
        studentDao = studentDataBase.studentDao()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

        allStudents = LivePagedListBuilder(studentDao.getAllStudent(), 2).build()

        allStudents.observe(this, Observer {
            adapter.submitList(it)
            it.addWeakCallback(null, object : PagedList.Callback() {
                override fun onChanged(position: Int, count: Int) {
                    Log.d("this", "$position ::: $count")
                }

                override fun onInserted(position: Int, count: Int) {
                }

                override fun onRemoved(position: Int, count: Int) {
                }
            })
        })


        add.setOnClickListener {
            for (i in 1..100) {
                AsyncTask.execute {
                    studentDao.insertStudent(Student(i))
                }
            }
        }


        clear.setOnClickListener {
            AsyncTask.execute {
                studentDao.deleteAllStudents()
            }
        }


    }
}
