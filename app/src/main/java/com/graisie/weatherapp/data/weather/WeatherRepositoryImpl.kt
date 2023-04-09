package com.graisie.weatherapp.data.weather

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.weather.api.WeatherApi
import com.graisie.weatherapp.data.weather.model.WeatherApiEntity
import com.graisie.weatherapp.domain.weather.model.WeatherEntity
import com.graisie.weatherapp.domain.weather.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val mapper: Mapper<WeatherApiEntity, WeatherEntity>,
) : WeatherRepository {

    override suspend fun getWeather(longitude: Double, latitude: Double): WeatherEntity {
        // for real project we need some abstractions that handles error here and uses
        // our own error types, for example, for better recognition of specific cases
        return weatherApi.getWeatherByCity(longitude, latitude, API_KEY)
            .let { mapper.map(it) }
    }

    companion object {
        private const val API_KEY = "526a8bd299afa7be37602fe3c971ecbd"
    }
}