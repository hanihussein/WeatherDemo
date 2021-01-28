package com.hani.weatherdemo.core.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private const val DATE_YYYYDDMM_FORMAT: String = "yyyy-MM-dd"

    private const val DATE_EEEEHHMM_FORMAT: String = "EEEE HH:mm"

    private const val DATE_MMM_FORMAT: String = "EEE"

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

    fun getDayName(date: String): String {

        var formattedDate: String? = null
        try {
            val originalFormat: DateFormat = SimpleDateFormat(DATE_YYYYDDMM_FORMAT, Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat(DATE_MMM_FORMAT, Locale.ENGLISH)
            val date: Date? = originalFormat.parse(date)
            formattedDate = targetFormat.format(date) // 20120821

        } catch (e: ParseException) {
            throw Exception(e.message)
        }
        return formattedDate
    }

    fun getCurrentDate(): String {

        var formattedDate: String? = null
        try {
            val originalFormat: DateFormat = SimpleDateFormat(DATE_EEEEHHMM_FORMAT, Locale.ENGLISH)
            formattedDate = originalFormat.format(Date());
        } catch (e: ParseException) {
            throw Exception(e.message)
        }
        return formattedDate
    }

}