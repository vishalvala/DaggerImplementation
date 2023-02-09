package com.example.daggerwithroom.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerwithroom.adapters.ProductAdapter
import com.example.daggerwithroom.databinding.ActivityMainBinding
import com.example.daggerwithroom.util.App
import com.example.daggerwithroom.util.Resource
import com.example.daggerwithroom.viewmodels.MainViewModel
import com.example.daggerwithroom.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var productAdapter: ProductAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as App).applicationComponent.injectMainActivity(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        productAdapter = ProductAdapter(this)
        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productAdapter
        }

        mainViewModel.product.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressbar.visibility = View.GONE
                    if (resource.data?.isNotEmpty() == true) {
                        productAdapter.differ.submitList(resource.data)
                        binding.rvProduct.visibility = View.VISIBLE
                        binding.groupNoData.visibility = View.GONE
                    } else {
                        binding.rvProduct.visibility = View.GONE
                        binding.groupNoData.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.progressbar.visibility = View.GONE
                    binding.rvProduct.visibility = View.GONE
                    binding.groupNoData.visibility = View.VISIBLE
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}