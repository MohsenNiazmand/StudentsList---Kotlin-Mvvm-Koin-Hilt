package com.example.studentslist.model.data.repositories

import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun refreshStudents(): Flow<BaseResult<List<Student>, ErrorResponse>>
    fun getStudents(): Flow<List<Student>>

}