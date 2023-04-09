package com.graisie.weatherapp.data.city.model

import com.google.gson.annotations.SerializedName

data class CityCoordinatesApiEntity(
    @SerializedName("lon")
    val longitude: Double,
    @SerializedName("lat")
    val latitude: Double,
)
