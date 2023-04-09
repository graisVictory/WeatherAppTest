package com.graisie.weatherapp.presentation.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graisie.weatherapp.domain.cities.repository.CitiesRepository
import com.graisie.weatherapp.presentation.cities.model.CityUiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CitiesState())
    val state: StateFlow<CitiesState> = _state
    private var job: Job? = null

    init {
        state.map { it.search }
            .distinctUntilChanged()
            .debounce(100)
            .onEach { getCities(it) }
            .launchIn(viewModelScope)

        getCities(null)
    }

    private fun getCities(search: String?) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                if (!isActive) {
                    return@launch
                }

                citiesRepository.getCities(search).mapIndexed() { index, city ->
                    // for user indexing starts from 1, not 0, so from user's perspective first
                    // row is uneven
                    val image = if ((index + 1) % 2 == 0) {
                        "https://infotech.gov.ua/storage/img/Temp3.png"
                    } else {
                        "https://infotech.gov.ua/storage/img/Temp1.png"
                    }
                    CityUiEntity(
                        city.id,
                        city.name,
                        image,
                        city.coordinates.longitude,
                        city.coordinates.latitude
                    )
                }
            }
                .onSuccess { cities ->
                    if (!isActive) {
                        return@launch
                    }
                    _state.update { current ->
                        current.copy(cities = cities, isLoading = false)
                    }
                }
                .onFailure {
                    if (!isActive) {
                        return@launch
                    }
                    _state.update { it.copy(isError = true, isLoading = false) }
                }
        }
    }

    fun searchCities(search: String?) {
        _state.update { it.copy(search = search) }
    }

    // Based on https://medium.com/androiddevelopers/viewmodel-one-off-event-antipatterns-16a1da869b95
    fun onErrorShown() {
        _state.update { it.copy(isError = false) }
    }
}
