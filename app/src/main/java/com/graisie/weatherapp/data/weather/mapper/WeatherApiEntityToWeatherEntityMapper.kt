package com.graisie.weatherapp.data.weather.mapper

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.weather.model.WeatherApiEntity
import com.graisie.weatherapp.data.weather.model.WeatherDescriptionApiEntity
import com.graisie.weatherapp.data.weather.model.WeatherMainDataApiEntity
import com.graisie.weatherapp.data.weather.model.WindApiEntity
import com.graisie.weatherapp.domain.weather.model.WeatherDescriptionEntity
import com.graisie.weatherapp.domain.weather.model.WeatherEntity
import com.graisie.weatherapp.domain.weather.model.WeatherMainDataEntity
import com.graisie.weatherapp.domain.weather.model.WindEntity
import javax.inject.Inject

class WeatherApiEntityToWeatherEntityMapper @Inject constructor(
    private val descriptionMapper: Mapper<WeatherDescriptionApiEntity, WeatherDescriptionEntity>,
    private val windMapper: Mapper<WindApiEntity, WindEntity>,
    private val mainDataMapper: Mapper<WeatherMainDataApiEntity, WeatherMainDataEntity>,
) : Mapper<WeatherApiEntity, WeatherEntity> {

    override fun map(from: WeatherApiEntity): WeatherEntity {
        return WeatherEntity(
            from.descriptions?.map { descriptionMapper.map(it) },
            from.base.orEmpty(),
            from.main.let { mainDataMapper.map((it)) },
            from.wind.let { windMapper.map((it)) },
            from.id,
            from.name.orEmpty(),
        )
    }
}
