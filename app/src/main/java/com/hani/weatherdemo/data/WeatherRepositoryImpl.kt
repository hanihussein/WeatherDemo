package com.hani.weatherdemo.data

import androidx.lifecycle.LiveData
import com.hani.weatherdemo.core.utils.ApiSuccessResponse
import com.hani.weatherdemo.core.utils.DataState
import com.hani.weatherdemo.core.utils.GenericApiResponse
import com.hani.weatherdemo.core.utils.NetworkBoundResource
import com.hani.weatherdemo.data.remote.LocationWeatherResponse
import com.hani.weatherdemo.data.remote.RemoteDataSource
import com.hani.weatherdemo.data.wrapper.toWeatherForecastModel
import com.hani.weatherdemo.domain.entities.WeatherForecastModel
import com.hani.weatherdemo.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val remoteDataSource: RemoteDataSource) : WeatherRepository {

    override fun getWeatherForecast(
            lat: Double,
            lon: Double
    ): LiveData<DataState<WeatherForecastModel>> {
        return object : NetworkBoundResource<LocationWeatherResponse, WeatherForecastModel>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<LocationWeatherResponse>) {
                result.value = DataState.data(data = response.body.toWeatherForecastModel())
            }

            override fun createCall(): LiveData<GenericApiResponse<LocationWeatherResponse>> {
                return remoteDataSource.getLocationForecast(lat, lon)
            }
        }.asLiveData()
    }
}