package com.example.justeatrestaurantlookupworking

import retrofit2.http.GET

interface ApiService {
    @GET("/discovery/uk/restaurants/enriched/bypostcode/EC4M7RF")
    suspend fun getRestaurants(
    ): RestaurantResponse
}