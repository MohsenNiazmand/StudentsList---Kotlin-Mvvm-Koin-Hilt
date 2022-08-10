package com.example.studentslist.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.services.ApiService
import com.example.studentslist.model.services.StudentDao
import io.reactivex.Completable
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    val studentDao: StudentDao
) : HomeDataSource {


    override fun refreshStudents(): Completable =
        apiService.getStudents().doOnSuccess { studentDao.insertAll(it) }.ignoreElement()

    override fun getStudents(): LiveData<List<Student>> {
        TODO("Not yet implemented")
    }


}