package com.example.daggerwithroom.api

import com.example.daggerwithroom.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    suspend fun getProducts(): Response<Product>
}