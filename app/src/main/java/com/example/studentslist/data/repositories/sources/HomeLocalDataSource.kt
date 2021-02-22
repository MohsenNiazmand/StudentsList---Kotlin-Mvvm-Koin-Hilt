package com.example.studentslist.data.repositories.sources

import com.example.studentslist.data.Student
import io.reactivex.Completable
import io.reactivex.Single

class HomeLocalDataSource:HomeDataSource {
    override fun getStudents(): Single<List<Student>> {
        TODO("Not yet implemented")
    }

    override fun refreshStudents(): Completable {
        TODO("Not yet implemented")
    }
}