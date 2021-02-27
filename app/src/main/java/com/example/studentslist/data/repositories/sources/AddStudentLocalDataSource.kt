package com.example.studentslist.data.repositories.sources

import com.example.studentslist.data.Student
import com.example.studentslist.services.StudentDao
import io.reactivex.Single

class AddStudentLocalDataSource(val studentDao: StudentDao):AddStudentDataSource {
    override fun save(firstName: String, lastName: String, course: String, score: String): Single<Student> {
        TODO("Not yet implemented")
    }
}