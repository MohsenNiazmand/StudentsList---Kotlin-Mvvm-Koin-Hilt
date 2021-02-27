package com.example.studentslist.services

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentslist.data.Student

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student : List<Student>)

    @Query("select * from students ORDER BY id DESC")
    fun getAll(): LiveData<List<Student>>
}