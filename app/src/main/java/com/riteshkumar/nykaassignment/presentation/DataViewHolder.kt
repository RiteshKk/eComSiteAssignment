package com.riteshkumar.nykaassignment.presentation

import android.graphics.Paint
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.riteshkumar.nykaassignment.R
import com.riteshkumar.nykaassignment.databinding.ListingItemViewBinding
import com.riteshkumar.nykaassignment.domain.model.Product

class DataViewHolder(
    private val binding: ListingItemViewBinding,
    private val onWishListClicked: ((Product) -> Unit)? = null,
    private val onAddToCartButtonClicked: ((Product) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Product) {
        binding.apply {
            productImage.load(item.imageUrl)
            productName.text = item.name
            discountedPrice.text = "₹${item.finalPrice}"

            itemPrice.apply {
                isVisible = item.price != item.finalPrice
                itemPrice.text = "₹${item.price}"
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            ratingBar.rating = item.rating.toFloat()
            ratingCount.text = "(${item.ratingCount})"

            val imageResource = if (item.showWishlistButton == 1) {
                R.drawable.heart_filled
            } else {
                R.drawable.heart
            }

            imageAddToWishList.apply {
                setImageResource(imageResource)
                setOnClickListener {
                    val value = if (item.showWishlistButton == 1) 0 else 1
                    val itemUpdated = item.copy(showWishlistButton = value)
                    onWishListClicked?.invoke(itemUpdated)
                }
            }

            btnAddToCart.apply {
                text = item.buttonText
                setOnClickListener {
                    onAddToCartButtonClicked?.invoke(item)
                }
            }
        }
    }
}