package com.example.justeatrestaurantlookupworking

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/discovery/uk/restaurants/enriched/bypostcode/EC4M7RF")
    suspend fun getRestaurants(
        @Query("limit") limit: Int
    ): RestaurantResponse
}