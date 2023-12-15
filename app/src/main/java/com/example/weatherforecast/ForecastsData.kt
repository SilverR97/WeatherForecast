package com.example.weatherforecast

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "forecasts")
data class ForecastsData(
    val latitude: Float,
    val longitude: Float,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)
