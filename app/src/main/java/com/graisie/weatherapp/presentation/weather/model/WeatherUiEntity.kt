package com.graisie.weatherapp.presentation.weather.model

data class WeatherUiEntity(
    val description: String,
    val currentTemperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val humidity: String,
    val windSpeed: String,
)