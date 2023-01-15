package com.riteshkumar.nykaassignment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.riteshkumar.nykaassignment.common.ERROR_MESSAGE
import com.riteshkumar.nykaassignment.common.NO_MORE_PRODUCTS
import com.riteshkumar.nykaassignment.databinding.LoadingViewBinding
import com.riteshkumar.nykaassignment.presentation.LoadingAdapter.LoadingViewHolder
import kotlinx.coroutines.flow.MutableStateFlow

class LoadingAdapter(
    private val pageCount: MutableStateFlow<Int>,
    private val totalItemCount: MutableStateFlow<Int>,
    private val retry: (() -> Unit)? = null
) : LoadStateAdapter<LoadingViewHolder>() {

    class LoadingViewHolder(
        private val binding: LoadingViewBinding,
        private val retry: (() -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            loadState: LoadState,
            pageCount: MutableStateFlow<Int>,
            totalItemCount: MutableStateFlow<Int>,
        ) {
            when (loadState) {
                is LoadState.Error -> {
                    if (pageCount.value == totalItemCount.value) {
                        binding.loadingText.isVisible = true
                        binding.loadingText.text = NO_MORE_PRODUCTS
                    }else {
                        binding.loadingText.isVisible = true
                        binding.loadingText.text = ERROR_MESSAGE
                        binding.btnRetry.isVisible = true
                        binding.btnRetry.setOnClickListener { retry?.invoke() }
                    }
                }
                is LoadState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.loadingText.isVisible = true
                    binding.loadingText.text = "Loading ${pageCount.value} of ${totalItemCount.value}"
                    binding.btnRetry.isVisible = false
                }
                else -> {
                    binding.progressBar.isVisible = false
                    binding.loadingText.isVisible = false
                    binding.btnRetry.isVisible = false
                }
            }
        }
    }

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.onBind(loadState, pageCount, totalItemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHolder {
        return LoadingViewHolder(
            LoadingViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry = retry
        )
    }
}
