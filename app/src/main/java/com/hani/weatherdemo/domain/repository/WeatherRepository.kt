package com.hani.weatherdemo.domain.repository

import androidx.lifecycle.LiveData
import com.hani.weatherdemo.core.utils.DataState
import com.hani.weatherdemo.data.remote.LocationWeatherResponse
import com.hani.weatherdemo.domain.entities.WeatherForecastModel

interface WeatherRepository {

    fun getWeatherForecast(lat: Double, lon: Double) : LiveData<DataState<WeatherForecastModel>>
}