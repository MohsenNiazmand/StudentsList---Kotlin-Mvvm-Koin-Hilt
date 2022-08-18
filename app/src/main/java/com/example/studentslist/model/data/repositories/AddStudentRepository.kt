package com.example.studentslist.model.data.repositories

import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import kotlinx.coroutines.flow.Flow

interface AddStudentRepository {
    suspend fun save(
        firstName: String,
        lastName: String,
        course: String,
        score: String
    ): Flow<BaseResult<Student, ErrorResponse>>

}