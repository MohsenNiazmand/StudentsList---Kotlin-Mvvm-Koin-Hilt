package com.example.studentslist.data.repositories.sources

import com.example.studentslist.data.Student
import io.reactivex.Single

interface AddStudentDataSource {
    fun save(firstName:String,lastName:String,course:String,score:String): Single<Student>

}