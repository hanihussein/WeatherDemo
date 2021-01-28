package com.hani.weatherdemo.data.wrapper

import com.hani.weatherdemo.core.utils.DateUtil
import com.hani.weatherdemo.data.remote.LocationWeatherResponse
import com.hani.weatherdemo.data.remote.TimeSeries
import com.hani.weatherdemo.domain.entities.DayForecastModel
import com.hani.weatherdemo.domain.entities.WeatherForecastModel


fun LocationWeatherResponse.toWeatherForecastModel(): WeatherForecastModel {


    val daysForecast: MutableMap<String, List<TimeSeries>> = properties.timeseries.groupBy {
        DateUtil.getDateStringFormat(it.time)
    }.toMutableMap()

    val upComingDaysForecast = daysForecast.map {
        getDayForecast(it)
    }.toMutableList()

    val currentDayIndex = 0
    val currentDayForecast = upComingDaysForecast.get(currentDayIndex)
    upComingDaysForecast.removeAt(currentDayIndex)

    return WeatherForecastModel(currentDayForecast, upComingDaysForecast)
}

fun getDayForecast(dateInfo: Map.Entry<String, List<TimeSeries>>): DayForecastModel {

    val maxTemp = dateInfo.value.maxOf { timeSeries: TimeSeries ->
        timeSeries.data.instant.details.air_temperature
    }

    val minTemp = dateInfo.value.minOf { timeSeries: TimeSeries ->
        timeSeries.data.instant.details.air_temperature
    }

    val maxWindSpeed = dateInfo.value.minOf { timeSeries: TimeSeries ->
        timeSeries.data.instant.details.wind_speed
    }

    val symbol = dateInfo.value[0].data.next_12_hours?.summary?.symbol_code // High Probability

    return DayForecastModel(dateInfo.key, maxTemp, minTemp, maxWindSpeed, symbol)

}