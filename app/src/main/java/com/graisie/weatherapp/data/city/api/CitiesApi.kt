package com.graisie.weatherapp.data.city.api

import com.graisie.weatherapp.data.city.model.CityApiEntity

interface CitiesApi {

    suspend fun getCities(searchQuery: String?): List<CityApiEntity>
}