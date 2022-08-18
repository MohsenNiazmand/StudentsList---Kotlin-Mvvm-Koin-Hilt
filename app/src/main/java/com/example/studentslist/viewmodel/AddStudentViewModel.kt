package com.example.studentslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.data.repositories.AddStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStudentViewModel @Inject constructor(private val addStudentRepository: AddStudentRepository) :
    ViewModel() {

    private val state = MutableStateFlow<AddStudentActivityState>(AddStudentActivityState.Init)
    val mState: StateFlow<AddStudentActivityState> get() = state


    private fun setLoading() {
        state.value = AddStudentActivityState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = AddStudentActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        state.value = AddStudentActivityState.ShowToast(message)
    }

    fun save(firstName: String, lastName: String, course: String, score: String) {
        viewModelScope.launch {
            addStudentRepository.save(firstName, lastName, course, score)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { baseResult ->
                    hideLoading()
                    when (baseResult) {
                        is BaseResult.Error -> state.value =
                            AddStudentActivityState.ErrorSave(baseResult.rawResponse)
                        is BaseResult.Success -> state.value =
                            AddStudentActivityState.SuccessSave(baseResult.data)
                    }

                }


        }

    }


}

sealed class AddStudentActivityState {
    object Init : AddStudentActivityState()
    data class IsLoading(val isLoading: Boolean) : AddStudentActivityState()
    data class ShowToast(val message: String) : AddStudentActivityState()
    data class SuccessSave(val student: Student) : AddStudentActivityState()
    data class ErrorSave(val rawResponse: ErrorResponse) : AddStudentActivityState()
}