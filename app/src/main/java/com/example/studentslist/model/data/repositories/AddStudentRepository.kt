package com.example.studentslist.model.data.repositories

import com.example.studentslist.model.data.Student
interface AddStudentRepository{
   suspend fun save(firstName:String,lastName:String,course:String,score:String): Student

}