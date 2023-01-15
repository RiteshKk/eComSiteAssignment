package com.riteshkumar.nykaassignment.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.riteshkumar.nykaassignment.R
import com.riteshkumar.nykaassignment.common.SPAN_COUNT_LANDSCAPE
import com.riteshkumar.nykaassignment.common.SPAN_COUNT_PORTRAIT
import com.riteshkumar.nykaassignment.databinding.ActivityMainBinding
import com.riteshkumar.nykaassignment.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[ListingViewModel::class.java]
    }

    private val listingAdapter by lazy {
        ListingAdapter(
            onWishListButtonClicked,
            onAddToCartClicked
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayUseLogoEnabled(true)
        }

        lifecycleScope.launch {
            viewModel.pagedDataFlow.collect { pagingData ->
                listingAdapter.submitData(pagingData)
            }
        }

        lifecycleScope.launchWhenCreated {
            listingAdapter.loadStateFlow.collect {
                binding.loadingView.isVisible = it.refresh is LoadState.Loading && listingAdapter.itemCount <= 0
            }
        }

        setupRecyclerView(listingAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupRecyclerView(listingAdapter: ListingAdapter) {
        with(binding.productList) {
            val orientation = resources.configuration.orientation
            val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) SPAN_COUNT_PORTRAIT else SPAN_COUNT_LANDSCAPE
            layoutManager = GridLayoutManager(this@MainActivity, spanCount).apply {
                spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (position) {
                            listingAdapter.itemCount -> 2
                            else -> 1
                        }
                    }
                }
            }
            adapter = listingAdapter.withLoadStateFooter(
                LoadingAdapter(viewModel.pageCount, viewModel.totalItemCount) {
                    listingAdapter.retry()
                }
            )
        }
    }

    private val onWishListButtonClicked = { product: Product ->
        //TODO: Update wishlist and change icon
        if (product.showWishlistButton == 1) {
            Toast.makeText(this@MainActivity, "Added to Wishlist", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@MainActivity, "Removed from Wishlist", Toast.LENGTH_LONG).show()
        }
    }

    private val onAddToCartClicked = { product: Product ->
        //TODO : add product to cart
        Toast.makeText(this@MainActivity, "Product Added to cart", Toast.LENGTH_LONG).show()
    }
}