package com.hani.weatherdemo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hani.weatherdemo.R
import com.hani.weatherdemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewmodel = weatherViewModel
        binding.lifecycleOwner = this

        weatherViewModel.setLocationInfo(57.7089, 11.9746)

        initCallback()
    }

    override fun onStart() {
        super.onStart()
    }


    fun initCallback() {

        weatherViewModel.dataState?.observe(this, { dataState ->
            dataState?.let {
                it.message?.let { message ->
                    showToast(message)
                }

                it.data?.let { weatherData ->
                    weatherViewModel.setWeatherInfo(weatherData)
                    main_view.visibility = View.VISIBLE
                }
                showLoadingView(it.loading)
            }
        })
    }


    private fun showLoadingView(isVisible: Boolean) {
        if (isVisible)
            loading_view.visibility = View.VISIBLE
        else
            loading_view.visibility = View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}