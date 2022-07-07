package com.example.studentslist.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentslist.data.Student
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Database(entities = [Student::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun studentDao() : StudentDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "students_db")
                        .fallbackToDestructiveMigration()
                        .build()

                INSTANCE = instance
                return instance
            }
        }



    }


}