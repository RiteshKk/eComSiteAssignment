package com.riteshkumar.nykaassignment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.riteshkumar.nykaassignment.databinding.ListingItemViewBinding
import com.riteshkumar.nykaassignment.domain.model.Product

class ListingAdapter(
    private val onWishListClicked: ((Product) -> Unit)? = null,
    private val onAddToCartButtonClicked: ((Product) -> Unit)? = null
) : PagingDataAdapter<Product, DataViewHolder>(diffCallback = diffUtil) {

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ListingItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onWishListClicked,
            onAddToCartButtonClicked
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}