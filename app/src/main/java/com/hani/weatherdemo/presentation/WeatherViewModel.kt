package com.hani.weatherdemo.presentation

import androidx.lifecycle.ViewModel
import com.hani.weatherdemo.domain.repository.WeatherRepository

class WeatherViewModel(private var weatherRepository: WeatherRepository) : ViewModel() {

    fun loadWeatherForecast(lat: Double, lon: Double) {
        weatherRepository.getWeatherForecast(lat,lon).observeForever {


        }
    }
}