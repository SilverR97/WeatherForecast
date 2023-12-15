package com.example.weatherforecast

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(dataSet: ForecastsData, weatherVm:AppVM){
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(title = {Text("Weather Forecast" )},
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary))
        //Spacer(Modifier.height(16.dp))

        /*Text(modifier = Modifier.padding(32.dp),
            fontSize = 20.sp,
            text = "Approved Time: xxxxxxxx",
            style = MaterialTheme.typography.bodySmall)

         */
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(modifier = Modifier.weight(1.0f),
            columns = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)) {
             itemsIndexed(items= listOf(dataSet)){index,item ->
                //items(2){index ->
                OutlinedCard(
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .padding(8.dp,4.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Surface() {
                        Column(){
                            Row(Modifier
                                .padding(8.dp)){
                                Text("text=dataSet.hourly_units.time")
                            }
                            Spacer(Modifier.height(10.dp))
                            Row(Modifier.fillMaxSize().padding(8.dp),horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text("Here goes the Image")
                                Text(
                                    text= dataSet.hourly_units.temperature_2m + "°C",
                                    fontWeight = FontWeight.Black
                                )

                            }
                        }

                    }
                }



            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically){
            var latitude by remember { mutableStateOf("")}
            Box(modifier= Modifier
                .width(125.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
            ) {

                    OutlinedTextField(value = latitude, onValueChange ={newLat->
                        latitude = newLat
                    }, label={ Text(text="Latitude")} )

                }
            Spacer(Modifier.width(10.dp))

            var longitude by remember { mutableStateOf("")}
            Box(modifier= Modifier
                .width(125.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
            ) {

                OutlinedTextField(value = longitude, onValueChange ={newLon->
                    longitude = newLon
                }, label={ Text(text="Longitude")} )

            }

            Spacer(Modifier.width(10.dp))

            Button(onClick = { if(latitude.isNotBlank()&& longitude.isNotBlank()){
                weatherVm.getForecastValues(latitude.toFloat(),longitude.toFloat())
                //weatherVm.getForecastValues(latitude,longitude)

            } },
                modifier= Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(10.dp)))
                {Text("Search")

                }
            Spacer(Modifier.width(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppScreen(){
    AppScreen(dataSet =ForecastsData(0.0f,0.0f,0.0,0.0, Hourly(emptyList(), emptyList()),HourlyUnits("",""),"","",0 ) , AppVM(WeatherDatabase()))
}
