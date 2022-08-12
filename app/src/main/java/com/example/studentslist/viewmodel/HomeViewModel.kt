package com.example.studentslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studentslist.model.common.StudentViewModel
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.data.repositories.HomeRepository
import com.example.studentslist.model.services.StudentDao
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(val homeRepository: HomeRepository) : StudentViewModel() {
    @Inject lateinit var studentDao: StudentDao
    val error = MutableLiveData<String>()
    val studentsLiveData=MutableLiveData<List<Student>>()
    val coroutineExceptionHandler= CoroutineExceptionHandler{coroutineContext, throwable ->
        error.postValue(throwable.message)
        progressBarLiveData.postValue(false)

    }
    init {
        progressBarLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO+coroutineExceptionHandler) {


                val students=homeRepository.refreshStudents()
                studentsLiveData.postValue(students)
                studentDao.insertAll(students)
                progressBarLiveData.postValue(false)



        }
    }

    fun getStudent(): LiveData<List<Student>>{
    return homeRepository.getStudents()
    }




}