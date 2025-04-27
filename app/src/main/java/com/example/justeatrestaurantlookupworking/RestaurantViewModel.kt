package com.example.justeatrestaurantlookupworking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {

    private val _restaurantAllData = MutableLiveData("No data")
    //val restaurantAllData: LiveData<String> get() = _restaurantAllData

    private val _restaurantFilteredByCuisine = MutableLiveData("No data")
    //val restaurantFilteredByCuisine: LiveData<String> get() = _restaurantFilteredByCuisine

    //-----Holds data to display on screen (updates here)
    private val _displayData = MutableLiveData("No data")
    val displayData: LiveData<String> get() = _displayData

    //-----Hold ALL initially returned Restaurants from response
    private var fullRestaurantList: List<Restaurant> = emptyList()

    fun getRestaurant(postCode: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getRestaurants(postCode, limit = 100) //100 returned
                fullRestaurantList = response.restaurants

                _restaurantAllData.value = fullRestaurantList.take(10).joinToString("\n\n") { restaurant ->
                    val cuisineNames = restaurant.cuisines.joinToString(", ") { it.name }
                    "${restaurant.name} \n" +
                            "Cuisines: $cuisineNames \n" +
                            "Address: ${restaurant.address.firstLine}, ${restaurant.address.postalCode}, ${restaurant.address.city} \n" +
                            "Star Rating: ${restaurant.rating.starRating}" }
                _displayData.value = _restaurantAllData.value

            } catch (e: Exception) {
                _displayData.value = "Error: ${e.message}"
            }
        }
    }

    fun filterByCuisine(cuisineChoice: String){
        val filteredList = fullRestaurantList.filter { restaurant ->
            restaurant.cuisines.any { cuisine ->
                cuisine.name.contains(cuisineChoice, ignoreCase = true)
            }
        }.take(10)
        _restaurantFilteredByCuisine.value = filteredList.joinToString("\n\n") { restaurant ->
            val cuisineNames = restaurant.cuisines.joinToString(", ") { it.name }
            "${restaurant.name} \n" +
                    "Cuisines: $cuisineNames \n" +
                    "Address: ${restaurant.address.firstLine}, ${restaurant.address.postalCode}, ${restaurant.address.city} \n" +
                    "Star Rating: ${restaurant.rating.starRating}" }
        _displayData.value = _restaurantFilteredByCuisine.value
        if (filteredList.isNotEmpty()){
            _restaurantFilteredByCuisine.value = "No restaurants found with cuisine: $cuisineChoice"
        }
    }
}