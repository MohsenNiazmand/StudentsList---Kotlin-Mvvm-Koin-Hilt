package com.example.studentslist.data.repositories

import com.example.studentslist.data.Student
import io.reactivex.Completable
import io.reactivex.Single

interface HomeRepository {

    fun getStudents(): Single<List<Student>>

    fun refreshStudents(): Completable
}