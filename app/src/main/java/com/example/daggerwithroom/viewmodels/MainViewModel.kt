package com.example.daggerwithroom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerwithroom.models.Product
import com.example.daggerwithroom.repository.ProductRepository
import com.example.daggerwithroom.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {
    val product: LiveData<Resource<Product>>
        get() = productRepository.products

    init {
        getProduct()
    }

    private fun getProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getProducts()
        }
    }
}