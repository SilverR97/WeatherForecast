package com.example.weatherforecast

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

/*interface AppViewModel{
    val dataState: StateFlow<>

}*/

class AppVM(private val wDataBase: WeatherDatabase) :ViewModel(){
    var pruebaDataSet: MutableLiveData<Response<List<ForecastsData>>> = MutableLiveData()
    var forecastDataList: List<ForecastsData> by mutableStateOf(listOf())
    private var errorMsg: String by mutableStateOf("")

    fun getForecastValues(latitude: Float, longitude: Float ){
        viewModelScope.launch {
           try{
               val response = wDataBase.getForecastData(latitude,longitude)
               forecastDataList = response
               //pruebaDataSet.value = response
           }
           catch (e:Exception){
               errorMsg = "Failed to fetch weather data."
           }
        }
    }

    fun getForecast(latitude: Float, longitude: Float ){
        viewModelScope.launch {
            val response2 = wDataBase.getForecast(latitude,longitude)
            pruebaDataSet.value = response2
        }
    }

    fun assignImage(){

    }

/*class FakeVM: AppViewModel{
override val
}*/

}