/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hani.weatherdemo.presentation

import androidx.databinding.BindingAdapter
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.hani.weatherdemo.R
import com.hani.weatherdemo.core.utils.DateUtil
import com.hani.weatherdemo.domain.entities.DayForecastModel

object BindingAdapters {

    @BindingAdapter("items")
    @JvmStatic
    fun setRecyclerViewItems(recyclerView: RecyclerView, items: MutableList<DayForecastModel>?) {
        items?.let {
            recyclerView.adapter = ForecastAdapter(it)
        }
    }

    @BindingAdapter("status")
    @JvmStatic
    fun setWeatherStatus(view: AppCompatTextView, status: String?) {
        status?.split("_")?.let {
            view.setText(
                view.resources.getString(
                    R.string.status,
                    DateUtil.getCurrentDate(),
                    it[0]
                )
            )
        }
    }

    @BindingAdapter(value = ["minTemp", "maxTemp"], requireAll = true)
    @JvmStatic
    fun setTemp(view: AppCompatTextView, minTemp: Float, maxTemp: Float) {
        view.setText(view.resources.getString(R.string._s_h_c, minTemp.toInt(), maxTemp.toInt()))
    }

    @BindingAdapter("speed")
    @JvmStatic
    fun setSpeed(view: AppCompatTextView, speed: Float) {
        view.setText(view.resources.getString(R.string.m_s, speed.toInt()))
    }

}
