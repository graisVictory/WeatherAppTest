package com.graisie.weatherapp.domain.weather.model

data class WeatherEntity(
    val descriptions: List<WeatherDescriptionEntity>?,
    val base: String,
    val main: WeatherMainDataEntity,
    val wind: WindEntity,
    val id: Int,
    val name: String,
)
