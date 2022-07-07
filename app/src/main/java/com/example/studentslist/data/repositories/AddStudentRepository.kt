package com.example.studentslist.data.repositories

import com.example.studentslist.AppModule
import com.example.studentslist.data.Student
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.Single
import javax.inject.Inject

interface AddStudentRepository{
    fun save(firstName:String,lastName:String,course:String,score:String): Single<Student>

}