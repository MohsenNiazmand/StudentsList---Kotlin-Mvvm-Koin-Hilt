package com.example.studentslist

import android.content.Context
import com.example.studentslist.data.repositories.AddStudentRepository
import com.example.studentslist.data.repositories.AddStudentRepositoryImpl
import com.example.studentslist.data.repositories.HomeRepositoryImpl
import com.example.studentslist.data.repositories.sources.*
import com.example.studentslist.services.ApiService
import com.example.studentslist.services.AppDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [DataModule::class,DataModule.Bindings::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getInstance(appContext).studentDao()


    @Singleton
    @Provides
    fun apiServiceInstance(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://expertdevelopers.ir/api/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


}