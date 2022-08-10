package com.example.studentslist.model.services

import com.example.studentslist.model.data.Student
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService{

    @GET("experts/student")

    fun getStudents() : Single<List<Student>>


    @POST("experts/student")

    fun save(@Body jsonObject: JsonObject) : Single<Student>



}


