package com.example.studentslist.data.repositories.sources

import com.example.studentslist.data.Student
import io.reactivex.Single

class AddStudentLocalDataSource:AddStudentDataSource {
    override fun save(firstName: String, lastName: String, course: String, score: String): Single<Student> {
        TODO("Not yet implemented")
    }
}