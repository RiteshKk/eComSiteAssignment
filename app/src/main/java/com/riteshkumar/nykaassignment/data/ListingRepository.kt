package com.riteshkumar.nykaassignment.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.riteshkumar.nykaassignment.common.DEFAULT_PAGE_SIZE
import com.riteshkumar.nykaassignment.data.remote.ListingApi
import com.riteshkumar.nykaassignment.domain.ListingPagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ListingRepository @Inject constructor(
    private val api: ListingApi
) {
    fun getListingData(
        pageCount: MutableStateFlow<Int>,
        totalItemCount: MutableStateFlow<Int>
    ) = Pager(
        config = PagingConfig(
            pageSize = 1,
            initialLoadSize = DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ListingPagingSource(pageCount, totalItemCount, api) }
    ).flow
}