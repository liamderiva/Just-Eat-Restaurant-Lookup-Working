package com.example.justeatrestaurantlookupworking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    private val _restaurantData = MutableLiveData("no data")
    val restaurantData: LiveData<String> get() = _restaurantData

    init {
        viewModelScope.launch { //Coroutine scope (launched asynchronously)
            getRestaurant("EC4M7RF")
        }
    }

    private suspend fun getRestaurant(postCode: String){
        _restaurantData.value = RetrofitClient.apiService.getRestaurants(postCode, limit = 1).toString()
    }
}