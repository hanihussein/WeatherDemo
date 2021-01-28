package com.hani.testing.utils

import com.hani.weatherdemo.data.remote.*

object TestUtil {
    const val TIMESTAMP_1 = "2021-01-28T11:55:21Z"
    const val CONVERTED_TIMESTAMP = "2021-01-28"
    const val GOTEBORG_LAT = 57.7089
    const val GOTEBORG_LON = 11.9746
    val WEATHER_RESPONSE_MOCK = LocationWeatherResponse("Feature", Properties(arrayListOf(
            TimeSeries("2021-01-28T12:00:00Z", TimeSeriesData(Instant(InstantDetails(Float.MAX_VALUE,
                    Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE)),
                    TimeSeriesSummary(Summary("clearsky_day")),
                    TimeSeriesSummary(Summary("clearsky_day")),
                    TimeSeriesSummary(Summary("clearsky_day"))))
    )))

}