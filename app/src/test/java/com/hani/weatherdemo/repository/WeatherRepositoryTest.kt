package com.hani.weatherdemo.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.IdlingRegistry
import com.hani.testing.utils.LiveDataTestUtil
import com.hani.testing.utils.TestUtil
import com.hani.weatherdemo.core.utils.DataState
import com.hani.weatherdemo.core.utils.EspressoIdlingResource
import com.hani.weatherdemo.data.WeatherRepositoryImpl
import com.hani.weatherdemo.data.remote.LocationWeatherResponse
import com.hani.weatherdemo.data.remote.RemoteDataSource
import com.hani.weatherdemo.domain.entities.WeatherForecastModel
import com.hani.weatherdemo.domain.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    // System under test
    lateinit var noteRepository: WeatherRepository
    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun initEach() {
        remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
        noteRepository = WeatherRepositoryImpl(remoteDataSource)
    }


    @ExperimentalCoroutinesApi
    @Test
    internal fun testRequestForecastInfo_returnTrue() {

        //Arrange
        val mockResponseLiveData = MutableLiveData<LocationWeatherResponse>()
        mockResponseLiveData.value = TestUtil.WEATHER_RESPONSE_MOCK
        Mockito.`when`<Any>(
            remoteDataSource.getLocationForecast(TestUtil.GOTEBORG_LAT, TestUtil.GOTEBORG_LON)
        ).thenReturn(mockResponseLiveData)

        //Act
        val repositoryLiveData =
            noteRepository.getWeatherForecast(TestUtil.GOTEBORG_LAT, TestUtil.GOTEBORG_LON)
        val loadingData =
            LiveDataTestUtil<DataState<WeatherForecastModel>>().getValue(repositoryLiveData)

        // Assert
        Mockito.verify(remoteDataSource)
            .getLocationForecast(TestUtil.GOTEBORG_LAT, TestUtil.GOTEBORG_LON)
        Mockito.verifyNoMoreInteractions(remoteDataSource)
        Assert.assertEquals(loadingData.loading, true)

    }
}