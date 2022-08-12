package com.example.studentslist.model.data.repositories

import androidx.lifecycle.LiveData
import com.example.studentslist.di.HomeLocalQualifier
import com.example.studentslist.di.HomeRemoteQualifier
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.data.repositories.sources.HomeDataSource
import io.reactivex.Completable
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor
    (
    @HomeRemoteQualifier private val homeRemoteDataSource: HomeDataSource,
    @HomeLocalQualifier private val homeLocalDataSource: HomeDataSource
) : HomeRepository {
    //    override fun refreshStudents(): Completable = homeRemoteDataSource.refreshStudents()
//
//    override fun getStudents(): LiveData<List<Student>> = homeLocalDataSource.getStudents()
    override suspend fun refreshStudents(): List<Student> {
        return homeRemoteDataSource.refreshStudents()
    }

    override fun getStudents(): LiveData<List<Student>> {
        return homeLocalDataSource.getStudents()
    }


}