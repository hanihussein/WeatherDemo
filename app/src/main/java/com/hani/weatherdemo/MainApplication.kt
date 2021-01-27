package com.hani.weatherdemo

import android.app.Application
import com.hani.weatherdemo.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MainApplication)

            modules(listOf(viewModelModule, repoModule, networkModule))
        }
    }

}




