package com.example.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppVmFactory(private val repository:WeatherDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppVM(repository) as T
    }
}