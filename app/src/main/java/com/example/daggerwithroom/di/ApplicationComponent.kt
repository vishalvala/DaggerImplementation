package com.example.daggerwithroom.di

import com.example.daggerwithroom.di.modules.NetworkModule
import com.example.daggerwithroom.di.modules.ProductModule
import com.example.daggerwithroom.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ProductModule::class])
interface ApplicationComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}