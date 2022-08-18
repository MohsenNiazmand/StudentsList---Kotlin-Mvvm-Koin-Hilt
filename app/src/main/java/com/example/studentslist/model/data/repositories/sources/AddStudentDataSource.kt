package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface AddStudentDataSource {
    suspend fun save(
        firstName: String,
        lastName: String,
        course: String,
        score: String
    ): Flow<BaseResult<Student, ErrorResponse>>
}