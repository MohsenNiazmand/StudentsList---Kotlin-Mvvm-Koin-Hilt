package com.example.studentslist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "students")
@Parcelize
data class Student(
    @PrimaryKey
    val id: Int,
    val course: String,
    val created_at: String,
    val first_name: String,
    val last_name: String,
    val score: Int,
    val updated_at: String
): Parcelable