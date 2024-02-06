package com.example.shop.data.network

import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getProducts() = apiService.getProducts()

}