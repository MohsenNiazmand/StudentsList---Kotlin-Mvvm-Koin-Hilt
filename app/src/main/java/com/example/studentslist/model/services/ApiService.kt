package com.example.studentslist.model.services

import com.example.studentslist.model.data.Student
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("experts/student")

    suspend fun getStudents(): Response<List<Student>>


    @POST("experts/student")

    suspend fun save(@Body jsonObject: JsonObject): Response<Student>


}


