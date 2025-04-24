package com.example.justeatrestaurantlookupworking

data class RestaurantResponse(
    val restaurants: List<Restaurant>,
)

data class Restaurant(
    val id: String,
    val name: String,
    val address: Address,
    val rating: Rating,
    val cuisines: List<Cuisine>,
)

data class Address(
    val city: String,
    val firstLine: String,
    val postalCode: String,
)

data class Rating(
    val starRating: Double,
)

data class Cuisine(
    val name: String,
)