package com.hani.weatherdemo.presentation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.hani.weatherdemo.R
import com.hani.weatherdemo.core.utils.DateUtil
import com.hani.weatherdemo.domain.entities.DayForecastModel

const val MAX_DAYS_COUNT = 7

class ForecastAdapter(var daysForeCasting: MutableList<DayForecastModel>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        val contactView: View = inflater.inflate(R.layout.rc_item_weather_day_info, parent, false)
        return ViewHolder(contactView)
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun onBind(dayForecastModel: DayForecastModel) {
            itemView.findViewById<AppCompatTextView>(R.id.tv_day_name).text =
                DateUtil.getDayName(dayForecastModel.date)

            itemView.findViewById<AppCompatTextView>(R.id.tv_weather_temp).text =
                itemView.resources.getString(
                    R.string._s_h_c,
                    dayForecastModel.minTemp.toInt(), dayForecastModel.maxTemp.toInt()
                )

            itemView.findViewById<AppCompatTextView>(R.id.tv_weather_speed).text =
                itemView.resources.getString(
                    R.string.m_s,
                    dayForecastModel.speed.toInt()
                )

        }
    }

}