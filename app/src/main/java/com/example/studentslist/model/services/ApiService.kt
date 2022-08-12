package com.example.studentslist.model.services

import androidx.lifecycle.LiveData
import com.example.studentslist.model.data.Student
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService{

    @GET("experts/student")

    suspend fun getStudents() :List<Student>


    @POST("experts/student")

    suspend fun save(@Body jsonObject: JsonObject) : Student



}


