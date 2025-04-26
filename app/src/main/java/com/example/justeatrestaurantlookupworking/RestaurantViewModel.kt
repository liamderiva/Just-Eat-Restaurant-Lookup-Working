package com.example.justeatrestaurantlookupworking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    //raw data
    private val _restaurantData = MutableLiveData("No data")
    val restaurantData: LiveData<String> get() = _restaurantData

    //just restaurant names
    private val _restaurantNames = MutableLiveData("No data")
    val restaurantNames: LiveData<String> get() = _restaurantNames

    private val _restaurantCuisines = MutableLiveData("No data")
    val restaurantCuisines: LiveData<String> get() = _restaurantCuisines

    private val _restaurantAllData = MutableLiveData("No data")
    val restaurantAllData: LiveData<String> get() = _restaurantAllData

    fun getRestaurant(postCode: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getRestaurants(postCode, limit = 2)
                //_restaurantData.value = response.toString()
                //_restaurantNames.value = response.restaurants.joinToString(separator = "\n") { it.name }

                _restaurantAllData.value = response.restaurants.joinToString(separator = "\n") { restaurant ->
                    val cuisineNames = restaurant.cuisines.joinToString(", ") { it.name }
                    "${restaurant.name} " +
                            "– $cuisineNames " +
                            "- ${restaurant.address.firstLine}, ${restaurant.address.postalCode}, ${restaurant.address.city} " +
                            "- ${restaurant.rating.starRating}" }

            } catch (e: Exception) {
                _restaurantData.value = "Error: ${e.message}"
            }
        }
    }
}