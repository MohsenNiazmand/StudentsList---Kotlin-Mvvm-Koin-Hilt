package com.example.studentslist.data.repositories.sources

import com.example.studentslist.data.Student
import com.example.studentslist.services.ApiService
import com.example.studentslist.services.StudentDao
import com.google.gson.JsonObject
import io.reactivex.Single

class AddStudentRemoteDataSource(val apiService: ApiService, val studentDao: StudentDao):AddStudentDataSource {
    override fun save(firstName: String, lastName: String, course: String, score: String): Single<Student> =
            apiService.save(JsonObject().apply {
                addProperty("first_name",firstName)
                addProperty("last_name",lastName)
                addProperty("course",course)
                addProperty("score",score)
            }        ).doOnSuccess {student -> studentDao.insert(student) }

}