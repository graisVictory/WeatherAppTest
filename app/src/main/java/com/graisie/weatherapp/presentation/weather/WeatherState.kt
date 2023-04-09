package com.graisie.weatherapp.presentation.weather

import com.graisie.weatherapp.presentation.weather.model.CoordinatesUiEntity
import com.graisie.weatherapp.presentation.weather.model.WeatherUiEntity

data class WeatherState(
    val weatherUiEntity: WeatherUiEntity? = null,
    val coordinates: CoordinatesUiEntity? = null,
    val isLoading: Boolean = true,
    // For real project should be error message or id of string resource
    // Based on https://medium.com/androiddevelopers/viewmodel-one-off-event-antipatterns-16a1da869b95
    val isError: Boolean = false,
)


