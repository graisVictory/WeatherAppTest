package com.graisie.weatherapp.data.city.mapper

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.city.model.CityApiEntity
import com.graisie.weatherapp.data.city.model.CityCoordinatesApiEntity
import com.graisie.weatherapp.domain.cities.model.CityCoordinatesEntity
import com.graisie.weatherapp.domain.cities.model.CityEntity
import javax.inject.Inject

class CityApiEntityToCityEntityMapper @Inject constructor(
    private val coordinatesMapper: Mapper<CityCoordinatesApiEntity, CityCoordinatesEntity>
) : Mapper<CityApiEntity, CityEntity> {

    override fun map(from: CityApiEntity): CityEntity {
        return CityEntity(
            from.id,
            from.name,
            from.state,
            from.countryCode,
            from.coordinates.let { coordinatesMapper.map(it) }
        )
    }
}