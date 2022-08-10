package com.example.studentslist.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.studentslist.model.data.Student
import io.reactivex.Completable

interface HomeDataSource {

    fun refreshStudents(): Completable
    fun getStudents(): LiveData<List<Student>>
}