package com.example.studentslist.addstudent

import com.example.studentslist.common.StudentViewModel
import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.AddStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
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