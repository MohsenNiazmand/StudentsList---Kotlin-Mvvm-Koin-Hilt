package com.example.studentslist.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.studentslist.model.data.Student
import io.reactivex.Completable

interface HomeDataSource {

   suspend fun refreshStudents():List<Student>
    fun getStudents(): LiveData<List<Student>>
}