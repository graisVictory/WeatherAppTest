package com.graisie.weatherapp.presentation.cities

import com.graisie.weatherapp.presentation.cities.model.CityUiEntity

data class CitiesState(
    val cities: List<CityUiEntity> = listOf(),
    val search: String? = null,
    val isLoading: Boolean = true,
    // For real project should be error message or id of string resource
    // Based on https://medium.com/androiddevelopers/viewmodel-one-off-event-antipatterns-16a1da869b95
    val isError: Boolean = false,
)
