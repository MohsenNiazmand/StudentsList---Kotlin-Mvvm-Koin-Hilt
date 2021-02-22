package com.example.studentslist.data.repositories

import com.example.studentslist.data.Student
import io.reactivex.Single

interface AddStudentRepository {
    fun save(firstName:String,lastName:String,course:String,score:String): Single<Student>

}