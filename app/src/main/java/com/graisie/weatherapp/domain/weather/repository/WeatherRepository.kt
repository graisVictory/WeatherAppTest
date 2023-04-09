package com.graisie.weatherapp.domain.weather.repository

import com.graisie.weatherapp.domain.weather.model.WeatherEntity

interface WeatherRepository {

    suspend fun getWeather(longitude: Double, latitude: Double): WeatherEntity
}