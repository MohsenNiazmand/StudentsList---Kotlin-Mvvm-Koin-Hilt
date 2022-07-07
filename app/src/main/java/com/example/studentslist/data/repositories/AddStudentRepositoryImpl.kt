package com.example.studentslist.data.repositories

import com.example.studentslist.AddStudentRemoteQualifier
import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.sources.AddStudentDataSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


class AddStudentRepositoryImpl @Inject constructor(@AddStudentRemoteQualifier private val remoteDataSource: AddStudentDataSource) :
    AddStudentRepository {
    override fun save(
        firstName: String,
        lastName: String,
        course: String,
        score: String
    ): Single<Student> =
        remoteDataSource.save(firstName, lastName, course, score)
}