package com.graisie.weatherapp.data.weather.mapper

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.weather.model.WindApiEntity
import com.graisie.weatherapp.domain.weather.model.WindEntity
import javax.inject.Inject

class WindApiEntityToWindEntityMapper @Inject constructor() : Mapper<WindApiEntity, WindEntity> {

    override fun map(from: WindApiEntity): WindEntity {
        return WindEntity(
            from.speed ?: 0.0,
            from.degrees ?: 0,
            from.gust ?: 0.0,
        )
    }
}
