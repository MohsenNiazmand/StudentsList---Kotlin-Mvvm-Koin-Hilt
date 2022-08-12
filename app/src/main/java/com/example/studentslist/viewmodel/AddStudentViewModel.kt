package com.example.studentslist.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.studentslist.model.common.StudentViewModel
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.data.repositories.AddStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddStudentViewModel @Inject constructor(private val addStudentRepository: AddStudentRepository) : StudentViewModel() {

   suspend fun save(firstName:String,lastName:String,course:String,score:String): Student {
        return addStudentRepository.save(firstName,lastName,course,score)
    }


}