package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.common.BaseResult
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.model.data.Student
import com.example.studentslist.model.services.ApiService
import com.example.studentslist.model.services.StudentDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    val studentDao: StudentDao
) : HomeDataSource {
    override suspend fun refreshStudents(): Flow<BaseResult<List<Student>, ErrorResponse>> {
        return flow {
            val response = apiService.getStudents()
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    studentDao.insertAll(body)
                    emit(BaseResult.Success(body))

                }
            } else {
                val type = object : TypeToken<ErrorResponse>() {}.type
                val err =
                    Gson().fromJson<ErrorResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

    override fun getStudents(): Flow<List<Student>> {
        TODO("Not yet implemented")
    }


}