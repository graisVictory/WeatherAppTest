package com.graisie.weatherapp.data.weather.mapper

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.weather.model.WeatherMainDataApiEntity
import com.graisie.weatherapp.domain.weather.model.WeatherMainDataEntity
import javax.inject.Inject

class WeatherMainDataApiEntityToMainDataEntityMapper @Inject constructor() :
    Mapper<WeatherMainDataApiEntity, WeatherMainDataEntity> {

    override fun map(from: WeatherMainDataApiEntity): WeatherMainDataEntity {
        return WeatherMainDataEntity(
            from.temperature ?: 0.0,
            from.feelsLike ?: 0.0,
            from.minTemperature ?: 0.0,
            from.maxTemperature ?: 0.0,
            from.pressure ?: 0,
            from.humidity ?: 0,
            from.seaLevel ?: 0,
            from.groundLevel ?: 0,
        )
    }
}
