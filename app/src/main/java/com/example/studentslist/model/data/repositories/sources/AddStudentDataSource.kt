package com.example.studentslist.model.data.repositories.sources

import com.example.studentslist.model.data.Student
import io.reactivex.Single

interface AddStudentDataSource {
    fun save(firstName:String,lastName:String,course:String,score:String): Single<Student>

}