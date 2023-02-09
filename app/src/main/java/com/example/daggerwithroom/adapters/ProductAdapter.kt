package com.example.daggerwithroom.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.daggerwithroom.R
import com.example.daggerwithroom.databinding.ItemProductBinding
import com.example.daggerwithroom.models.ProductItem

class ProductAdapter(
    private val context: Context
) : Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding: ItemProductBinding) : ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.binding.apply {
            Glide.with(context).load(product.image).into(imgProduct)
            tvTitle.text = product.title
            tvDescription.text = product.description
            tvPrice.text = context.getString(R.string.product_price, product.price.toString())
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}