package com.hani.weatherdemo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hani.weatherdemo.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherViewModel.loadWeatherForecast(57.7089 , 11.9746)
    }
}