package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.services.StudentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(val studentDao: StudentDao) : HomeDataSource {
    override suspend fun refreshStudents(): Flow<BaseResult<List<Student>, ErrorResponse>> {
        TODO("Not yet implemented")
    }

    override fun getStudents(): Flow<List<Student>> {
        return studentDao.getAll()
    }

}