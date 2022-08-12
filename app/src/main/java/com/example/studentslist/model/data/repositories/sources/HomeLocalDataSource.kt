package com.example.studentslist.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.services.StudentDao
import io.reactivex.Completable
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(val studentDao: StudentDao):HomeDataSource {

    //    override fun refreshStudents(): Completable {
//        TODO("Not yet implemented")
//    }
//
//    override fun getStudents(): LiveData<List<Student>> = studentDao.getAll()
    override suspend fun refreshStudents(): List<Student> {
        TODO("Not yet implemented")
    }

    override fun getStudents(): LiveData<List<Student>> {
        return studentDao.getAll()
    }

}