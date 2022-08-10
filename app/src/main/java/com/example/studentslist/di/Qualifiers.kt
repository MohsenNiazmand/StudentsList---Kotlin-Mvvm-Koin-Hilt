package com.example.studentslist.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeRemoteQualifier()


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeLocalQualifier()

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AddStudentRemoteQualifier()

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AddStudentLocalQualifier()
