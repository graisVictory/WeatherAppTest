package com.graisie.weatherapp.data.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherApiEntity(
    @SerializedName("weather")
    val descriptions: List<WeatherDescriptionApiEntity>?,
    val base: String?,
    val main: WeatherMainDataApiEntity,
    val wind: WindApiEntity,
    val id: Int,
    val name: String?,
)
