package com.graisie.weatherapp.data.city.model

import com.google.gson.annotations.SerializedName

data class CityApiEntity(
    val id: Int,
    val name: String,
    val state: String,
    @SerializedName("country")
    val countryCode: String,
    @SerializedName("coord")
    val coordinates: CityCoordinatesApiEntity,
)
