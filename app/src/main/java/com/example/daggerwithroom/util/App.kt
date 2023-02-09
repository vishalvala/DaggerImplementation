package com.example.daggerwithroom.util

import android.app.Application
import com.example.daggerwithroom.di.ApplicationComponent
import com.example.daggerwithroom.di.DaggerApplicationComponent

class App : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
            applicationComponent = DaggerApplicationComponent.builder().build()
    }
}