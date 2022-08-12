package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.data.Student
import com.example.studentslist.model.services.StudentDao
import io.reactivex.Single
import javax.inject.Inject

class AddStudentLocalDataSource @Inject constructor(val studentDao: StudentDao):AddStudentDataSource {
    override suspend fun save(
        firstName: String,
        lastName: String,
        course: String,
        score: String
    ): Student {
        TODO("Not yet implemented")
    }

}