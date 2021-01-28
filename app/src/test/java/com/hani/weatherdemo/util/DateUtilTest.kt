package com.hani.weatherdemo.util

import android.text.TextUtils
import com.hani.testing.utils.TestUtil
import com.hani.weatherdemo.core.utils.DateUtil
import org.junit.Assert
import org.junit.Test
import java.lang.reflect.Executable
import java.text.ParseException
import java.util.*

class DateUtilTest {

    @Test
    fun testFormatDate_returnStringDate() {

        // arrange
        val currentTime = Date()

        //Arrange
        val newFormattedDate = DateUtil.formatDate(currentTime)

        //Assert
        Assert.assertNotNull(newFormattedDate)
    }


    @Test
    fun testCurrentDate_returnCorrectFormat() {

        // arrange
        val currentFormattedDate = DateUtil.formatDate(Date())

        //Arrange
        val newFormattedDate = DateUtil.getCurrentDate()

        //Assert
        Assert.assertEquals(currentFormattedDate, newFormattedDate)
    }


    @Test(expected = Exception::class)
    fun testDayNameWithWrongDate_throwException() {

        // arrange
        val stringDate = ""

        //Arrange
        DateUtil.getDayName(stringDate)

        //Assert
        //ThrowException
    }

    @Test
    fun testConvertTimestampToDateFormat_returnNewDate() {

        // arrange
        val timeStamp = TestUtil.TIMESTAMP_1

        //Arrange
        val convertedDate = DateUtil.getDateStringFormat(timeStamp)

        //Assert
        Assert.assertEquals(convertedDate, TestUtil.CONVERTED_TIMESTAMP)
    }
}