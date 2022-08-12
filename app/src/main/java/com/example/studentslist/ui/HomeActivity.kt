package com.example.studentslist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslist.R
import com.example.studentslist.model.common.StudentActivity
import com.example.studentslist.model.data.Student
import com.example.studentslist.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : StudentActivity() {
    val studentAdapter = StudentAdapter()
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_main_students.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        rv_main_students.adapter = studentAdapter


        homeViewModel.getStudent().observe(this) {
            Timber.i(it.toString())
            studentAdapter.students = it as ArrayList<Student>
            homeViewModel.progressBarLiveData.value = false
        }

        homeViewModel.error.observe(this){
            Timber.e(it)
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }



        homeViewModel.progressBarLiveData.observe(this) {
            setProgressIndicator(it)
        }

        fab_main_addNewStudent.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))

        })


    }
}