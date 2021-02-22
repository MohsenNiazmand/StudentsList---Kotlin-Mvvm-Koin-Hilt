package com.example.studentslist.addstudent

import com.example.studentslist.common.StudentViewModel
import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.AddStudentRepository
import io.reactivex.Single

class AddStudentViewModel(val addStudentRepository: AddStudentRepository) : StudentViewModel() {

    fun save(firstName:String,lastName:String,course:String,score:String): Single<Student> {
        progressBarLiveData.value=true
        return addStudentRepository.save(firstName,lastName,course,score).doFinally {
            progressBarLiveData.postValue(false)
        }

    }


}