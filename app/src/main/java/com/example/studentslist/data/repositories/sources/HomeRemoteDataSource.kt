package com.example.studentslist.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.studentslist.data.Student
import com.example.studentslist.services.ApiService
import com.example.studentslist.services.StudentDao
import io.reactivex.Completable
import io.reactivex.Single

class HomeRemoteDataSource(val apiService: ApiService , val studentDao: StudentDao):HomeDataSource {



    override fun refreshStudents(): Completable =apiService.getStudents().doOnSuccess { studentDao.insert(it) }.ignoreElement()

    override fun getStudents(): LiveData<List<Student>> {
        TODO("Not yet implemented")
    }


}