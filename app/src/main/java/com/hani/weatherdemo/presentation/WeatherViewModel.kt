package com.hani.weatherdemo.presentation

import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hani.weatherdemo.core.models.LocationInfo
import com.hani.weatherdemo.core.utils.DataState
import com.hani.weatherdemo.domain.entities.WeatherForecastModel
import com.hani.weatherdemo.domain.repository.WeatherRepository

class WeatherViewModel(private var weatherRepository: WeatherRepository) : ViewModel() {

    private val _stateEvent: MutableLiveData<LocationInfo> = MutableLiveData()
    private val _weatherInfoLiveData: MutableLiveData<WeatherForecastModel> = MutableLiveData()
    val weatherInfoLiveData: LiveData<WeatherForecastModel>
        get() = _weatherInfoLiveData

    var dataState: LiveData<DataState<WeatherForecastModel>>? =
        Transformations.switchMap(_stateEvent) {
            weatherRepository.getWeatherForecast(it.lat, it.lon)
        }

    fun setLocationInfo(lat: Double, lon: Double) {
        _stateEvent.value = LocationInfo(lat, lon)
    }

    fun setWeatherInfo(weatherForecastModel: WeatherForecastModel) {
        _weatherInfoLiveData.value = weatherForecastModel
    }
}