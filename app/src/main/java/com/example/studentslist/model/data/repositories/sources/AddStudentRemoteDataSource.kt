package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.data.Student
import com.example.studentslist.model.services.ApiService
import com.example.studentslist.model.services.StudentDao
import com.google.gson.JsonObject
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddStudentRemoteDataSource @Inject constructor(
    val apiService: ApiService,
    val studentDao: StudentDao
) : AddStudentDataSource {
    override suspend fun save(
        firstName: String,
        lastName: String,
        course: String,
        score: String
    ): Student {
        return apiService.save(JsonObject().apply {
            addProperty("first_name", firstName)
            addProperty("last_name", lastName)
            addProperty("course", course)
            addProperty("score", score)
        })


            .also { student -> studentDao.insert(student) }
    }


}