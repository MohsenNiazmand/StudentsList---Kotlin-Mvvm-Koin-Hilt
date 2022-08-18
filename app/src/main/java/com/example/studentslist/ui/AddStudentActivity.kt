package com.example.studentslist.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.studentslist.R
import com.example.studentslist.model.common.StudentActivity
import com.example.studentslist.model.common.extension.showGenericAlertDialog
import com.example.studentslist.model.common.extension.showToast
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.viewmodel.AddStudentActivityState
import com.example.studentslist.viewmodel.AddStudentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddStudentActivity : StudentActivity() {
    private val addStudentViewModel: AddStudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        save()
        observe()

    }

    private fun save() {

        fab_addNewStudent_save.setOnClickListener {
            if (et_addNewStudent_firstName.length() > 0
                && et_addNewStudent_lastName.length() > 0
                && et_addNewStudent_course.length() > 0
                && et_addNewStudent_score.length() > 0
            ) {

                addStudentViewModel.save(
                    et_addNewStudent_firstName.text.toString(),
                    et_addNewStudent_lastName.text.toString(),
                    et_addNewStudent_course.text.toString(),
                    et_addNewStudent_score.text.toString()
                )
            }
        }
    }

    private fun observe() {
        addStudentViewModel.mState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: AddStudentActivityState) {
        when (state) {
            is AddStudentActivityState.Init -> Unit
            is AddStudentActivityState.ErrorSave -> handleErrorSave(state.rawResponse)
            is AddStudentActivityState.SuccessSave -> handleSuccessSave(state.student)
            is AddStudentActivityState.ShowToast -> showToast(state.message)
            is AddStudentActivityState.IsLoading -> handleLoading(state.isLoading)
        }
    }


    private fun handleErrorSave(response: ErrorResponse) {
        showGenericAlertDialog(response.message)
    }

    private fun handleSuccessSave(studentEntity: Student) {
        showToast("Welcome ${studentEntity.first_name + " " + studentEntity.last_name}")
        goToMainActivity()
    }


    private fun goToMainActivity() {
        startActivity(Intent(this@AddStudentActivity, HomeActivity::class.java))
        finish()
    }

    private fun handleLoading(isLoading: Boolean) {
        fab_addNewStudent_save.isEnabled = !isLoading
        setProgressIndicator(isLoading)
        if (!isLoading) {
            setProgressIndicator(false)
        }
    }
}