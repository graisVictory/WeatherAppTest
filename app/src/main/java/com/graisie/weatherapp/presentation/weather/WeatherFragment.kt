package com.graisie.weatherapp.presentation.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.graisie.weatherapp.R
import com.graisie.weatherapp.databinding.FragmentWeatherBinding
import com.graisie.weatherapp.presentation.weather.model.CoordinatesUiEntity
import com.graisie.weatherapp.presentation.weather.model.WeatherUiEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding.mapView.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.state
                .map { it.isError }
                .filter { it }
                .collectLatest {
                    Toast.makeText(requireContext(), R.string.error_msg, Toast.LENGTH_LONG).show()
                    viewModel.onErrorShown()
                }
        }
        lifecycleScope.launch {
            viewModel.state
                .map { it.isLoading }
                .distinctUntilChanged()
                .collectLatest { binding.progressBar.isVisible = it }
        }
        // Separate observers so we don't update data that hasn't been changed
        lifecycleScope.launch {
            viewModel.state
                .map { it.weatherUiEntity }
                .distinctUntilChanged()
                .filterNotNull()
                .collectLatest { updateWeatherData(it) }
        }
        lifecycleScope.launch {
            viewModel.state
                .map { it.coordinates }
                .distinctUntilChanged()
                .filterNotNull()
                .collectLatest { updateCoordinates(it) }
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun updateWeatherData(weather: WeatherUiEntity) {
        binding.tvDescription.text = weather.description
        binding.tvCurrentTemp.text = getString(R.string.current_temp, weather.currentTemperature)
        binding.tvMaxTemp.text = getString(R.string.max_temp, weather.maxTemperature)
        binding.tvMinTemp.text = getString(R.string.min_temp, weather.minTemperature)
        binding.tvHumidity.text = getString(R.string.humidity, weather.humidity)
        binding.tvWindSpeed.text = getString(R.string.wind_speed, weather.windSpeed)
    }

    private fun updateCoordinates(coordinates: CoordinatesUiEntity) {
        binding.mapView.getMapAsync { map ->
            val latLon = LatLng(coordinates.latitude, coordinates.longitude)
            map.addMarker(MarkerOptions().position(latLon))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLon, 12f))
        }
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroyView() {
        binding.mapView.onDestroy()
        super.onDestroyView()
    }
}
