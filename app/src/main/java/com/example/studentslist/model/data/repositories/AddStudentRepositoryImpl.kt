package com.example.studentslist.model.data.repositories

import com.example.studentslist.di.AddStudentRemoteQualifier
import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.data.repositories.sources.AddStudentDataSource
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AddStudentRepositoryImpl @Inject constructor(@AddStudentRemoteQualifier private val remoteDataSource: AddStudentDataSource) :
    AddStudentRepository {
    override suspend fun save(
        firstName: String,
        lastName: String,
        course: String,
        score: String
    ): Flow<BaseResult<Student, ErrorResponse>> {
        return remoteDataSource.save(firstName, lastName, course, score)
    }
}