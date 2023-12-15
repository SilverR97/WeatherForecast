package com.example.weatherforecast

import androidx.room.Dao
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

@Dao
interface WeatherAPI {
    @GET("forecast")
    fun getForecastData(@Query("Latitude") latitude: Float,
                        @Query("Longitude") longitude: Float): List<ForecastsData>

    @GET("forecast")
    fun getForecast(@Query("Latitude") latitude: Float,
                        @Query("Longitude") longitude: Float): Response<List<ForecastsData>>

    companion object ResponseWeatherApI{
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            //.baseUrl("https://maceo.sth.kth.se/weather/forecast?lonLat=lon/14.333/lat/60.383")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherAPI:WeatherAPI = retrofit.create(WeatherAPI::class.java)
    }
}


