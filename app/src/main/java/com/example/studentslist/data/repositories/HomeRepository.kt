package com.example.studentslist.data.repositories

import androidx.lifecycle.LiveData
import com.example.studentslist.data.Student
import io.reactivex.Completable
import io.reactivex.Single

interface HomeRepository {

    fun refreshStudents(): Completable


    fun getStudents(): LiveData<List<Student>>

}