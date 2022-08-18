package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.services.StudentDao
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddStudentLocalDataSource @Inject constructor(val studentDao: StudentDao) :
    AddStudentDataSource {
    override suspend fun save(
        firstName: String,
        lastName: String,
        course: String,
        score: String
    ): Flow<BaseResult<Student, ErrorResponse>> {
        TODO("Not yet implemented")
    }


}