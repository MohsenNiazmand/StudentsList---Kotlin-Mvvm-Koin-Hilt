package com.example.studentslist.model.data.repositories

import com.example.studentslist.di.HomeLocalQualifier
import com.example.studentslist.di.HomeRemoteQualifier
import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.data.repositories.sources.HomeDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor
    (
    @HomeRemoteQualifier private val homeRemoteDataSource: HomeDataSource,
    @HomeLocalQualifier private val homeLocalDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun refreshStudents(): Flow<BaseResult<List<Student>, ErrorResponse>> {
        return homeRemoteDataSource.refreshStudents()
    }

    override fun getStudents(): Flow<List<Student>> {
        return homeLocalDataSource.getStudents()
    }


}