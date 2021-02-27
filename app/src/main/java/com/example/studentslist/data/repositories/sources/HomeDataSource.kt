package com.example.studentslist.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.studentslist.data.Student
import io.reactivex.Completable

interface HomeDataSource {


    fun refreshStudents(): Completable

    fun getStudents(): LiveData<List<Student>>



}