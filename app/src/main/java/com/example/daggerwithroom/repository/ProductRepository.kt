package com.example.daggerwithroom.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daggerwithroom.api.ApiInterface
import com.example.daggerwithroom.models.Product
import com.example.daggerwithroom.util.Resource
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    private var _productLiveData: MutableLiveData<Resource<Product>> = MutableLiveData()
    val products: LiveData<Resource<Product>>
        get() = _productLiveData

    suspend fun getProducts() {
        _productLiveData.postValue(Resource.Loading())
        val response = apiInterface.getProducts()
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let {
                _productLiveData.postValue(Resource.Success(it))
                return
            }
            _productLiveData.postValue(Resource.Error(response.message()))
        }

    }
}