package com.graisie.weatherapp.data.city

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.city.api.CitiesApi
import com.graisie.weatherapp.data.city.model.CityApiEntity
import com.graisie.weatherapp.domain.cities.model.CityEntity
import com.graisie.weatherapp.domain.cities.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesApi: CitiesApi,
    private val cityApiEntityToCityEntityMapper: Mapper<CityApiEntity, CityEntity>
) : CitiesRepository {

    override suspend fun getCities(searchQuery: String?): List<CityEntity> {
        return citiesApi.getCities(searchQuery)
            .let { cities -> cities.map { cityApiEntityToCityEntityMapper.map(it) } }
    }
}
