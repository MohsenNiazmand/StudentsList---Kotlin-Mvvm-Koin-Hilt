package com.example.studentslist.viewmodel

import com.example.studentslist.model.common.StudentViewModel
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.data.repositories.AddStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import javax.inject.Inject

@HiltViewModel
class AddStudentViewModel @Inject constructor(private val addStudentRepository: AddStudentRepository) : StudentViewModel() {



    fun save(firstName:String,lastName:String,course:String,score:String): Single<Student> {
        progressBarLiveData.value=true
        return addStudentRepository.save(firstName,lastName,course,score).doFinally {
            progressBarLiveData.postValue(false)
        }

    }


}