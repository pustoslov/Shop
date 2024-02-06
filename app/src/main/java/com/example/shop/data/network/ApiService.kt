package com.example.shop.data.network

import com.example.shop.data.network.model.Products
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val URL = "https://run.mocky.io/v3/"
    }

    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getProducts(): Products
}