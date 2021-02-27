package com.example.studentslist.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslist.R
import com.example.studentslist.addstudent.AddStudentActivity
import com.example.studentslist.common.StudentActivity
import com.example.studentslist.data.Student
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeActivity : StudentActivity() {
    val studentAdapter = StudentAdapter()
    val homeViewModel:HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_main_students.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        rv_main_students.adapter = studentAdapter

        homeViewModel.getStudent().observe(this){
            Timber.i(it.toString())
            studentAdapter.students = it as ArrayList<Student>
        }

        homeViewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

        fab_main_addNewStudent.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))

        })


    }
}