package com.graisie.weatherapp.data.weather.mapper

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.weather.model.WeatherDescriptionApiEntity
import com.graisie.weatherapp.domain.weather.model.WeatherDescriptionEntity
import javax.inject.Inject

class WeatherDescriptionApiToEntityMapper @Inject constructor() :
    Mapper<WeatherDescriptionApiEntity, WeatherDescriptionEntity> {

    override fun map(from: WeatherDescriptionApiEntity): WeatherDescriptionEntity {
        return WeatherDescriptionEntity(
            from.id,
            from.main.orEmpty(),
            from.description.orEmpty(),
            from.icon.orEmpty(),
        )
    }
}
