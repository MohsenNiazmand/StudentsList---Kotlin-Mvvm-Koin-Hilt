package com.example.studentslist.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.studentslist.data.Student
import com.example.studentslist.services.StudentDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(val studentDao: StudentDao):HomeDataSource {

    override fun refreshStudents(): Completable {
        TODO("Not yet implemented")
    }

    override fun getStudents(): LiveData<List<Student>> = studentDao.getAll()
}