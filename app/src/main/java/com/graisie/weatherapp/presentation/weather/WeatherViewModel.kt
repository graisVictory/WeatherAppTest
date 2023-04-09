package com.graisie.weatherapp.presentation.weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graisie.weatherapp.domain.weather.repository.WeatherRepository
import com.graisie.weatherapp.domain.usecase.UseCase
import com.graisie.weatherapp.presentation.cities.model.CityUiEntity
import com.graisie.weatherapp.presentation.weather.model.CoordinatesUiEntity
import com.graisie.weatherapp.presentation.weather.model.WeatherUiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val weatherRepository: WeatherRepository,
    private val formatDoubleUseCase: UseCase<Double, String>,
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state

    init {
        savedStateHandle.get<CityUiEntity>("city")?.let {
            CoordinatesUiEntity(it.longitude, it.latitude)
        }?.apply {
            _state.update { it.copy(coordinates = this) }
            getWeather(latitude, longitude)
        }
    }

    private fun getWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { weatherRepository.getWeather(longitude, latitude) }
                .map { weather ->
                    WeatherUiEntity(
                        weather.descriptions?.joinToString { it.description }.orEmpty(),
                        formatDoubleUseCase(weather.main.temperature),
                        formatDoubleUseCase(weather.main.maxTemperature),
                        formatDoubleUseCase(weather.main.minTemperature),
                        weather.main.humidity.toString(),
                        formatDoubleUseCase(weather.wind.speed),
                    )
                }
                .onSuccess { weather ->
                    _state.update { current ->
                        current.copy(isLoading = false, weatherUiEntity = weather)
                    }
                }
                .onFailure {
                    _state.update { current -> current.copy(isLoading = false, isError = true) }
                }
        }
    }

    // Based on https://medium.com/androiddevelopers/viewmodel-one-off-event-antipatterns-16a1da869b95
    fun onErrorShown() {
        _state.update { it.copy(isError = false) }
    }
}
