package com.example.studentslist

import android.app.Application
import com.example.studentslist.data.repositories.HomeRepository
import com.example.studentslist.data.repositories.HomeRepositoryImpl
import com.example.studentslist.data.repositories.sources.HomeRemoteDataSource
import com.example.studentslist.home.HomeViewModel
import com.example.studentslist.services.apiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())


        val myModules = module {
            single { apiServiceInstance() }
            factory<HomeRepository> {
                HomeRepositoryImpl(HomeRemoteDataSource(get() )) }

            viewModel { HomeViewModel(get()) }

        }
        startKoin {
            androidContext(this@App)
            modules(myModules)

        }

    }



}