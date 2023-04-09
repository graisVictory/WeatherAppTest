package com.graisie.weatherapp.di

import com.graisie.weatherapp.common.Mapper
import com.graisie.weatherapp.data.city.CitiesRepositoryImpl
import com.graisie.weatherapp.data.city.api.CitiesApi
import com.graisie.weatherapp.data.city.api.CitiesApiImpl
import com.graisie.weatherapp.data.city.mapper.CityApiEntityToCityEntityMapper
import com.graisie.weatherapp.data.city.mapper.CityCoordinatesApiEntityToCoordinatesEntityMapper
import com.graisie.weatherapp.data.city.model.CityApiEntity
import com.graisie.weatherapp.data.city.model.CityCoordinatesApiEntity
import com.graisie.weatherapp.data.weather.WeatherRepositoryImpl
import com.graisie.weatherapp.data.weather.mapper.WeatherApiEntityToWeatherEntityMapper
import com.graisie.weatherapp.data.weather.mapper.WeatherDescriptionApiToEntityMapper
import com.graisie.weatherapp.data.weather.mapper.WeatherMainDataApiEntityToMainDataEntityMapper
import com.graisie.weatherapp.data.weather.mapper.WindApiEntityToWindEntityMapper
import com.graisie.weatherapp.data.weather.model.WeatherApiEntity
import com.graisie.weatherapp.data.weather.model.WeatherDescriptionApiEntity
import com.graisie.weatherapp.data.weather.model.WeatherMainDataApiEntity
import com.graisie.weatherapp.data.weather.model.WindApiEntity
import com.graisie.weatherapp.domain.cities.model.CityCoordinatesEntity
import com.graisie.weatherapp.domain.cities.model.CityEntity
import com.graisie.weatherapp.domain.cities.repository.CitiesRepository
import com.graisie.weatherapp.domain.usecase.FormatDoubleUseCase
import com.graisie.weatherapp.domain.usecase.UseCase
import com.graisie.weatherapp.domain.weather.model.WeatherDescriptionEntity
import com.graisie.weatherapp.domain.weather.model.WeatherEntity
import com.graisie.weatherapp.domain.weather.model.WeatherMainDataEntity
import com.graisie.weatherapp.domain.weather.model.WindEntity
import com.graisie.weatherapp.domain.weather.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    @Singleton
    abstract fun bindsCitiesRepository(impl: CitiesRepositoryImpl): CitiesRepository

    @Binds
    @Singleton
    abstract fun bindsWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindsCitiesApi(impl: CitiesApiImpl): CitiesApi

    @Binds
    @Singleton
    abstract fun bindsCityMapper(
        impl: CityApiEntityToCityEntityMapper
    ): Mapper<CityApiEntity, CityEntity>

    @Binds
    @Singleton
    abstract fun bindsCoordinatesMapper(
        impl: CityCoordinatesApiEntityToCoordinatesEntityMapper
    ): Mapper<CityCoordinatesApiEntity, CityCoordinatesEntity>

    @Binds
    @Singleton
    abstract fun bindsDescriptionMapper(
        impl: WeatherDescriptionApiToEntityMapper
    ): Mapper<WeatherDescriptionApiEntity, WeatherDescriptionEntity>

    @Binds
    @Singleton
    abstract fun bindsMainDataMapper(
        impl: WeatherMainDataApiEntityToMainDataEntityMapper
    ): Mapper<WeatherMainDataApiEntity, WeatherMainDataEntity>

    @Binds
    @Singleton
    abstract fun bindsWindMapper(
        impl: WindApiEntityToWindEntityMapper
    ): Mapper<WindApiEntity, WindEntity>

    @Binds
    @Singleton
    abstract fun bindsWeatherMapper(
        impl: WeatherApiEntityToWeatherEntityMapper
    ): Mapper<WeatherApiEntity, WeatherEntity>

    @Binds
    @Singleton
    abstract fun bindsFormatDoubleUseCase(
        impl: FormatDoubleUseCase
    ): UseCase<Double, String>
}
