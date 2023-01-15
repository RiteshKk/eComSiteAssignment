package com.riteshkumar.nykaassignment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.riteshkumar.nykaassignment.data.ListingRepository
import com.riteshkumar.nykaassignment.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val repository: ListingRepository
) : ViewModel() {

    val pageCount = MutableStateFlow(1)
    val totalItemCount = MutableStateFlow(0)

    val pagedDataFlow: Flow<PagingData<Product>>
        get() = repository.getListingData(pageCount, totalItemCount).cachedIn(viewModelScope)
}