package com.example.studentslist.data.repositories

import androidx.lifecycle.LiveData
import com.example.studentslist.HomeLocalQualifier
import com.example.studentslist.HomeRemoteQualifier
import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.sources.HomeDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor
    (
    @HomeRemoteQualifier private val homeRemoteDataSource: HomeDataSource,
    @HomeLocalQualifier private val homeLocalDataSource: HomeDataSource
) : HomeRepository {
    override fun refreshStudents(): Completable = homeRemoteDataSource.refreshStudents()

    override fun getStudents(): LiveData<List<Student>> = homeLocalDataSource.getStudents()


}