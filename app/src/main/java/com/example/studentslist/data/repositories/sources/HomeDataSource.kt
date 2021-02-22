package com.example.studentslist.data.repositories.sources

import com.example.studentslist.data.Student
import io.reactivex.Completable
import io.reactivex.Single

interface HomeDataSource {

    fun getStudents(): Single<List<Student>>

    fun refreshStudents(): Completable

}