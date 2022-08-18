package com.example.studentslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val homeRepository: HomeRepository) : ViewModel() {

    private val state = MutableStateFlow<HomeActivityState>(HomeActivityState.Init)
    val mState: StateFlow<HomeActivityState> get() = state


    private fun setLoading() {
        state.value = HomeActivityState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = HomeActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        state.value = HomeActivityState.ShowToast(message)
    }

    init {
        refresh()
        getStudent()
    }

    fun getStudent() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getStudents()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())

                }
                .collect { baseResult ->
                    hideLoading()
                    state.value = HomeActivityState.SuccessLoadFromDataBase(baseResult)
                    if (baseResult.isEmpty()) {
                        state.value = HomeActivityState.ShowEmptyState(Unit)
                    }
                }
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.refreshStudents()
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
                            HomeActivityState.ErrorLoad(baseResult.rawResponse)
                        is BaseResult.Success -> state.value =
                            HomeActivityState.SuccessLoadFromServer(baseResult.data)
                    }
                }
        }
    }


}

sealed class HomeActivityState {
    object Init : HomeActivityState()
    data class IsLoading(val isLoading: Boolean) : HomeActivityState()
    data class ShowToast(val message: String) : HomeActivityState()
    data class ShowEmptyState(val x: Unit) : HomeActivityState()
    data class SuccessLoadFromServer(val student: List<Student>) : HomeActivityState()
    data class SuccessLoadFromDataBase(val student: List<Student>) : HomeActivityState()
    data class ErrorLoad(val rawResponse: ErrorResponse) : HomeActivityState()
}