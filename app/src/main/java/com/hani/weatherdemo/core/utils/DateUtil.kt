package com.hani.weatherdemo.core.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private const val DATE_YYYYDDMM_FORMAT: String = "yyyy-dd-mm"
    private const val DATE_SERVER_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss"

    fun getDateStringFormat(fullTime: String): String {

        var formattedDate: String? = null
        try {
            val originalFormat: DateFormat = SimpleDateFormat(DATE_SERVER_FORMAT, Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat(DATE_YYYYDDMM_FORMAT, Locale.ENGLISH)
            val date: Date? = originalFormat.parse(fullTime)
            formattedDate = targetFormat.format(date) // 20120821

        } catch (e: ParseException) {
            throw Exception(e.message)
        }
        return formattedDate
    }

}