package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {

    suspend fun refreshStudents(): Flow<BaseResult<List<Student>, ErrorResponse>>
    fun getStudents(): Flow<List<Student>>
}