package com.graisie.weatherapp.data.weather.api

import com.graisie.weatherapp.data.weather.model.WeatherApiEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): WeatherApiEntity
}