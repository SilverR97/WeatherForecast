package com.example.weatherforecast

data class Hourly(
    val temperature_2m: List<Double>,
    val time: List<String>
)