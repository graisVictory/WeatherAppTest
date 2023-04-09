package com.graisie.weatherapp.data.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherMainDataApiEntity(
    @SerializedName("temp")
    val temperature: Double?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("temp_min")
    val minTemperature: Double?,
    @SerializedName("temp_max")
    val maxTemperature: Double?,
    val pressure: Int?,
    val humidity: Int?,
    @SerializedName("sea_level")
    val seaLevel: Int?,
    @SerializedName("grnd_level")
    val groundLevel: Int?
)