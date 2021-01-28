package com.hani.weatherdemo.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hani.weatherdemo.R
import com.hani.weatherdemo.core.utils.DateUtil
import com.hani.weatherdemo.databinding.RcItemWeatherDayInfoBinding
import com.hani.weatherdemo.domain.entities.DayForecastModel

const val MAX_DAYS_COUNT = 7

class ForecastAdapter(var daysForeCasting: MutableList<DayForecastModel>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RcItemWeatherDayInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(daysForeCasting[position])
    }

    override fun getItemCount(): Int {
        var count = 0
        if ((daysForeCasting.size > MAX_DAYS_COUNT))
            count = MAX_DAYS_COUNT
        else count = daysForeCasting.size
        return count
    }

    class ViewHolder(private val binding: RcItemWeatherDayInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(dayForecastModel: DayForecastModel) {
            binding.tvDayName.text =
                DateUtil.getDayName(dayForecastModel.date)

            binding.tvWeatherTemp.text =
                itemView.resources.getString(
                    R.string._s_h_c,
                    dayForecastModel.minTemp.toInt(), dayForecastModel.maxTemp.toInt()
                )

            binding.tvWeatherSpeed.text =
                itemView.resources.getString(
                    R.string.m_s,
                    dayForecastModel.speed.toInt()
                )

            BindingAdapters.setImage(binding.tvWeatherPic, dayForecastModel.symbol_code)
        }
    }

}