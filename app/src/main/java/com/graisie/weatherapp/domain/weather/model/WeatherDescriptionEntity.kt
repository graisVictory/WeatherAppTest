package com.graisie.weatherapp.domain.weather.model

data class WeatherDescriptionEntity(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
