package com.example.studentslist.data.repositories.sources

import com.example.studentslist.data.Student
import com.example.studentslist.services.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class HomeRemoteDataSource(val apiService: ApiService):HomeDataSource {
    override fun getStudents(): Single<List<Student>> = apiService.getStudents()


    override fun refreshStudents(): Completable {
        TODO("Not yet implemented")
    }


}