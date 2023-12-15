package com.example.weatherforecast

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import retrofit2.Response

class WeatherDatabase {
    suspend fun getForecastData(latitude: Float, longitude: Float): List<ForecastsData> {
        return WeatherAPI.weatherAPI.getForecastData(latitude,longitude)
    }

    suspend fun getForecast(latitude: Float, longitude: Float): Response<List<ForecastsData>> {
        return WeatherAPI.weatherAPI.getForecast(latitude,longitude)
    }
}