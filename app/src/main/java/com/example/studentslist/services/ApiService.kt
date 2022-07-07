package com.example.studentslist.services

import com.example.studentslist.data.Student
import com.google.gson.JsonObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

interface ApiService{

    @GET("experts/student")

    fun getStudents() : Single<List<Student>>


    @POST("experts/student")

    fun save(@Body jsonObject: JsonObject) : Single<Student>



}


