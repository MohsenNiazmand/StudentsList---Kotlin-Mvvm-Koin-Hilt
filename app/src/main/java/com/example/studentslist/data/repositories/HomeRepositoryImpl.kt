package com.example.studentslist.data.repositories

import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.sources.HomeDataSource
import io.reactivex.Completable
import io.reactivex.Single

class HomeRepositoryImpl(val remoteDataSource: HomeDataSource):HomeRepository {
    override fun getStudents(): Single<List<Student>> {
        return remoteDataSource.getStudents()
    }

    override fun refreshStudents(): Completable {
        TODO("Not yet implemented")
    }


}