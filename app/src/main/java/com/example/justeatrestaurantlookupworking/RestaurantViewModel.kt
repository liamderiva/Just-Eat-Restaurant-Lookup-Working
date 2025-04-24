package com.example.justeatrestaurantlookupworking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    private val _restaurantData = MutableLiveData("No data")
    val restaurantData: LiveData<String> get() = _restaurantData

    fun getRestaurant(postCode: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getRestaurants(postCode, limit = 2)
                _restaurantData.value = response.toString()
            } catch (e: Exception) {
                _restaurantData.value = "Error: ${e.message}"
            }
        }
    }
}