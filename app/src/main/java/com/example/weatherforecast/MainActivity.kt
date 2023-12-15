package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherforecast.ui.theme.WeatherForecastTheme
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: AppVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val forecasts = ForecastsData(59f,18f,0.0,0.0, Hourly(emptyList(), emptyList()),HourlyUnits("",""),"","",0)
                    val dataList = listOf(forecasts)
                    val repository = WeatherDatabase()
                    val viewModelFactory = AppVmFactory(repository)
                    viewModel = ViewModelProvider(this,viewModelFactory).get(AppVM::class.java)
                    //viewModel.getForecastValues()
                    AppScreen(dataSet = forecasts , weatherVm = viewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview(){
    WeatherForecastTheme{
        val example = ForecastsData(59f,18f,0.0,0.0, Hourly(emptyList(), emptyList()),HourlyUnits("°c","iso8601"),"GMT","GMT",0)
    }
}
