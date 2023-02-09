package com.example.daggerwithroom.di.modules

import com.example.daggerwithroom.api.ApiInterface
import com.example.daggerwithroom.repository.ProductRepository
import dagger.Module
import dagger.Provides

@Module
class ProductModule {
    @Provides
    fun provideProductRepository(apiInterface: ApiInterface) = ProductRepository(apiInterface)
}