package com.example.studentslist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslist.R
import com.example.studentslist.model.common.StudentActivity
import com.example.studentslist.model.common.extension.showGenericAlertDialog
import com.example.studentslist.model.common.extension.showToast
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.viewmodel.HomeActivityState
import com.example.studentslist.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeActivity : StudentActivity() {
    val studentAdapter = StudentAdapter()
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        observe()
        goToActivityAddStudent()

    }

    private fun goToActivityAddStudent() {
        fab_main_addNewStudent.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        rv_main_students.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_main_students.adapter = studentAdapter
    }

    private fun observe() {
        homeViewModel.mState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: HomeActivityState) {
        when (state) {
            is HomeActivityState.Init -> Unit
            is HomeActivityState.ErrorLoad -> handleErrorSave(state.rawResponse)
            is HomeActivityState.SuccessLoadFromServer -> handleSuccessSave(state.student)
            is HomeActivityState.SuccessLoadFromDataBase -> handleSuccessLoadFromServer(state.student)
            is HomeActivityState.ShowToast -> showToast(state.message)
            is HomeActivityState.ShowEmptyState -> showEmptyState()
            is HomeActivityState.IsLoading -> handleLoading(state.isLoading)
        }
    }


    private fun handleSuccessLoadFromServer(students: List<Student>) {
        studentAdapter.students = students as ArrayList<Student>
        handleLoading(false)

    }

    private fun showEmptyState() {
        rv_main_students.visibility = View.INVISIBLE
        fab_main_addNewStudent.isEnabled = true
        tvEmptyList.visibility = View.VISIBLE
        handleLoading(false)
    }

    private fun handleSuccessSave(studentEntity: List<Student>) {
        handleLoading(false)
    }

    private fun handleErrorSave(response: ErrorResponse) {
        showGenericAlertDialog(response.message)
    }

    private fun handleLoading(isLoading: Boolean) {
        fab_main_addNewStudent.isEnabled = !isLoading
        setProgressIndicator(isLoading)
        if (!isLoading) {
            setProgressIndicator(false)
        }
    }

}