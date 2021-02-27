package com.example.studentslist.data.repositories

import androidx.lifecycle.LiveData
import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.sources.HomeDataSource
import com.example.studentslist.data.repositories.sources.HomeLocalDataSource
import com.example.studentslist.data.repositories.sources.HomeRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

class HomeRepositoryImpl(val homeRemoteDataSource: HomeDataSource,
val homeLocalDataSource: HomeDataSource):HomeRepository {
    override fun refreshStudents(): Completable = homeRemoteDataSource.refreshStudents()

    override fun getStudents(): LiveData<List<Student>> = homeLocalDataSource.getStudents()


}