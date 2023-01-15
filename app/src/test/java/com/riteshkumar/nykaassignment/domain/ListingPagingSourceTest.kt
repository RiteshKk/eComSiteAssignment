package com.riteshkumar.nykaassignment.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource.LoadParams.Append
import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingSource.LoadResult.Page
import com.riteshkumar.nykaassignment.data.ListingRepository
import com.riteshkumar.nykaassignment.data.remote.ListingApi
import com.riteshkumar.nykaassignment.data.remote.dto.ListingResponseDto
import com.riteshkumar.nykaassignment.data.remote.dto.ProductDto
import com.riteshkumar.nykaassignment.data.remote.dto.ResponseDto
import com.riteshkumar.nykaassignment.data.remote.dto.toProduct
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
internal class ListingPagingSourceTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val api: ListingApi = mockk(relaxed = true)
    private lateinit var listingRepository: ListingRepository
    private lateinit var listingPagingSource: ListingPagingSource

    private val pageCount: MutableStateFlow<Int> = MutableStateFlow(1)
    private val totalItemCount: MutableStateFlow<Int> = MutableStateFlow(0)

    companion object {
        val listingResponse = ListingResponseDto(
            status = "success",
            message = "Success",
            type = "object",
            response = ResponseDto(
                productCount = 1,
                totalFound = 1,
                offset = 0,
                products = listOf(
                    ProductDto(
                        rating = 4.0,
                        objectType = "product",
                        categoryIds = "4362,4408,6619,6621,6631,9816",
                        id = "282534",
                        sku = "9781578261406",
                        ratingCount = 0,
                        type = "simple",
                        finalPrice = 899,
                        price = 899,
                        buttonText = "ADD TO BAG",
                        discount = 0,
                        showWishlistButton = 0,
                        name = "The Body Sculpting Bible Swimsuit Workout: Women's Edition by James Villepigue - Paperback",
                        imageUrl = "https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781578261406.jpg",
                        quantity = 1
                    ),
                )
            )
        )
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        listingRepository = ListingRepository(api)
        listingPagingSource = ListingPagingSource(pageCount, totalItemCount, api)
    }

    @Test
    fun `listing paging source append - success`() = runTest {
        coEvery { (api.getListingData(2)) }.returns(listingResponse)
        val expectedResult = Page(
            data = listingResponse.response?.products?.map { it.toProduct() } ?: emptyList(),
            prevKey = 1,
            nextKey = 3
        )

        Assert.assertEquals(
            expectedResult, listingPagingSource.load(
                Append(
                    key = 2,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `listing paging source refresh - success`() = runTest {
        coEvery { (api.getListingData(1)) }.returns(listingResponse)
        val expectedResult = Page(
            data = listingResponse.response?.products?.map { it.toProduct() } ?: emptyList(),
            prevKey = null,
            nextKey = 2
        )
        Assert.assertEquals(
            expectedResult, listingPagingSource.load(
                Refresh(
                    key = 1,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}