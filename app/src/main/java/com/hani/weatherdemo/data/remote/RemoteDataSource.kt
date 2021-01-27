package com.hani.weatherdemo.data.remote

import androidx.lifecycle.LiveData
import com.hani.weatherdemo.BuildConfig
import com.hani.weatherdemo.core.utils.GenericApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface RemoteDataSource {

    @Headers("User-Agent:${BuildConfig.APPLICATION_ID}")
    @GET("complete")
    fun getLocationForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): LiveData<GenericApiResponse<LocationWeatherResponse>>


}