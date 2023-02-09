package com.example.daggerwithroom.di.modules

import com.example.daggerwithroom.api.ApiInterface
import com.example.daggerwithroom.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit) : ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }
}