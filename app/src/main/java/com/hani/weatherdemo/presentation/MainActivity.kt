package com.hani.weatherdemo.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.hani.weatherdemo.R
import com.hani.weatherdemo.core.Consts
import com.hani.weatherdemo.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = weatherViewModel
        binding.lifecycleOwner = this

        initCallback()
        weatherViewModel.setLocationInfo(Consts.GOTHENBURG_LAT, Consts.GOTHENBURG_LON)
    }

    private fun initCallback() {

        weatherViewModel.dataState?.observe(this, { dataState ->
            dataState?.let {
                it.message?.let { message ->
                    showMessage(message)
                }

                it.data?.let { weatherData ->
                    weatherViewModel.setWeatherInfo(weatherData)
                    binding.mainView.visibility = View.VISIBLE
                }
                showLoadingView(it.loading)
            }
        })
    }


    private fun showLoadingView(isVisible: Boolean) {
        if (isVisible)
            binding.loadingView.visibility = View.VISIBLE
        else
            binding.loadingView.visibility = View.GONE
    }

    private fun showMessage(message: String) {
        Snackbar
            .make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) {
                weatherViewModel.setLocationInfo(Consts.GOTHENBURG_LAT, Consts.GOTHENBURG_LON)
            }.show()
    }
}