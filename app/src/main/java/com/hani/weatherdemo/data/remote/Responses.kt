package com.hani.weatherdemo.data.remote

data class LocationWeatherResponse(
        var type: String,
        val properties: Properties
)

data class Properties(val meta: Meta, val timeseries: ArrayList<TimeSeries>)

data class Meta(var updated_at: String, val units: Units)

data class Units(
        val air_pressure_at_sea_level: String,
        val air_temperature: String,
        val cloud_area_fraction: String,
        val precipitation_amount: String,
        val relative_humidity: String,
        val wind_from_direction: String,
        val wind_speed: String,
)

data class TimeSeries(val time: String, val data: TimeSeriesData)

data class TimeSeriesData(
        val instant: Instant,
        val next_12_hours: TimeSeriesSummary?,
        val next_1_hours: TimeSeriesSummary,
        val next_6_hours: TimeSeriesSummary

)

data class TimeSeriesSummary(val summary: Summary)

data class Summary(val symbol_code: String)

data class Instant(val details: InstantDetails)

data class InstantDetails(
        val air_pressure_at_sea_level: Float,
        val air_temperature: Float,
        val cloud_area_fraction: Float,
        val relative_humidity: Float,
        val wind_from_direction: Float,
        val wind_speed: Float,
)
