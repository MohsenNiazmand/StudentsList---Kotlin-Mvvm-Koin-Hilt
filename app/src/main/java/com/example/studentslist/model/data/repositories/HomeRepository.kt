package com.example.studentslist.model.data.repositories

import androidx.lifecycle.LiveData
import com.example.studentslist.model.data.Student
import io.reactivex.Completable

interface HomeRepository {

    fun refreshStudents(): Completable


    fun getStudents(): LiveData<List<Student>>

}