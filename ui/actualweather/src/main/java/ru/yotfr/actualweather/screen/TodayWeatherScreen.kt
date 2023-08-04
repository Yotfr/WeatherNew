package ru.yotfr.actualweather.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.yotfr.actualweather.viewmodel.TodayWeatherViewModel


@Composable
fun TodayWeatherScreen(vm: TodayWeatherViewModel) {
    val screenState by vm.screenState.collectAsState()

    LaunchedEffect(Unit) {
        vm.openedScreen()
    }

    Column {
        Text(text = "Temperature ${screenState.selectedWeatherModel?.temperature}")
        LazyRow {
            items(screenState.hourlyForecast) {
                Column {
                    Text(text = it.time.hour.toString())
                    Text(text = it.temperature.toString())
                }
            }
        }
    }
}