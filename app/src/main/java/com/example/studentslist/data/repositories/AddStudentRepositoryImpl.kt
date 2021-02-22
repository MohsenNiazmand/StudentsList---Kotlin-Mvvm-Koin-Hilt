package com.example.studentslist.data.repositories

import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.sources.AddStudentDataSource
import io.reactivex.Single

class AddStudentRepositoryImpl(val remoteDataSource : AddStudentDataSource) : AddStudentRepository {
    override fun save(firstName: String, lastName: String, course: String, score: String): Single<Student> =
            remoteDataSource.save(firstName, lastName, course, score)
}