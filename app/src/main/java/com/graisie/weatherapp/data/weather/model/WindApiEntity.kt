package com.graisie.weatherapp.data.weather.model

import com.google.gson.annotations.SerializedName

data class WindApiEntity(
    val speed: Double?,
    @SerializedName("deg")
    val degrees: Int?,
    val gust: Double?
)
