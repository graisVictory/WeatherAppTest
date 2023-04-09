package com.graisie.weatherapp.data.city.mapper

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.city.model.CityCoordinatesApiEntity
import com.graisie.weatherapp.domain.cities.model.CityCoordinatesEntity
import javax.inject.Inject

class CityCoordinatesApiEntityToCoordinatesEntityMapper @Inject constructor() :
    Mapper<CityCoordinatesApiEntity, CityCoordinatesEntity> {

    override fun map(from: CityCoordinatesApiEntity): CityCoordinatesEntity {
        return CityCoordinatesEntity(from.longitude, from.latitude)
    }
}
