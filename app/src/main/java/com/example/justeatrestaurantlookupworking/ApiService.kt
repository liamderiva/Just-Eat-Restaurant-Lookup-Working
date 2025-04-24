package com.example.justeatrestaurantlookupworking

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/discovery/uk/restaurants/enriched/bypostcode/{postcode}")
    suspend fun getRestaurants(
        @Path("postcode") postcode: String,
        @Query("limit") limit: Int
    ): RestaurantResponse
}