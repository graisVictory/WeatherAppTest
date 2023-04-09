package com.graisie.weatherapp.domain.cities.repository

import com.graisie.weatherapp.domain.cities.model.CityEntity

interface CitiesRepository {

    suspend fun getCities(searchQuery: String?): List<CityEntity>
}
