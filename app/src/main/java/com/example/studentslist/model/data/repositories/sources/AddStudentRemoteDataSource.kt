package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.services.ApiService
import com.example.studentslist.model.services.StudentDao
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    ): Flow<BaseResult<Student, ErrorResponse>> {
        return flow {
            val response = apiService.save(JsonObject().apply {
                addProperty("first_name", firstName)
                addProperty("last_name", lastName)
                addProperty("course", course)
                addProperty("score", score)
            })
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(BaseResult.Success(body))
                studentDao.insert(body)
            } else {
                val type = object : TypeToken<ErrorResponse>() {}.type
                val err: ErrorResponse = Gson().fromJson(response.errorBody()!!.charStream(), type)
                emit(BaseResult.Error(err))
            }
        }
    }


}