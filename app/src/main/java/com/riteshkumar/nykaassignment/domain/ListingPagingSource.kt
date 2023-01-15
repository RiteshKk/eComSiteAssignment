package com.riteshkumar.nykaassignment.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.riteshkumar.nykaassignment.common.DEFAULT_PAGE_SIZE
import com.riteshkumar.nykaassignment.data.remote.ListingApi
import com.riteshkumar.nykaassignment.data.remote.dto.toProduct
import com.riteshkumar.nykaassignment.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.io.IOException

class ListingPagingSource(
    private val pageCount: MutableStateFlow<Int>,
    private val totalItemCount: MutableStateFlow<Int>,
    private val api: ListingApi
) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.nextKey?.plus(1)
                ?: state.closestPageToPosition(it)?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val position = params.key ?: 1
        return try {
            val response = api.getListingData(
                page = position
            )
            pageCount.emit(position * DEFAULT_PAGE_SIZE)
            totalItemCount.emit(response.response?.totalFound ?: 0)
            val productListData = response.response?.products?.map { it.toProduct() } ?: emptyList()
            val nextKey = if (productListData.size < params.loadSize) null else position + 1
            val prevKey = if (position == 1) null else position - 1
            LoadResult.Page(
                productListData,
                prevKey,
                nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}