package com.graisie.weatherapp.presentation.cities.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityUiEntity(
    val id: Int,
    val name: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
) : Parcelable
