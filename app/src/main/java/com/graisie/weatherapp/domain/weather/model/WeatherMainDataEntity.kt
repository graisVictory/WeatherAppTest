package com.graisie.weatherapp.domain.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherMainDataEntity(
    val temperature: Double,
    val feelsLike: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val seaLevel: Int,
    val groundLevel: Int
)