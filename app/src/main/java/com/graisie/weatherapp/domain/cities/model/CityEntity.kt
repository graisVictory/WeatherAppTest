package com.graisie.weatherapp.domain.cities.model

data class CityEntity(
    val id: Int,
    val name: String,
    val state: String,
    val countryCode: String,
    val coordinates: CityCoordinatesEntity,
)