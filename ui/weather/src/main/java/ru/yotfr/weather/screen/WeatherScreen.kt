package ru.yotfr.weather.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.shared.R
import ru.yotfr.shared.model.PartOfTheDay
import ru.yotfr.shared.model.ThreeHourWeatherModel
import ru.yotfr.shared.model.WeatherType
import ru.yotfr.weather.viewmodel.WeatherViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    vm: WeatherViewModel = hiltViewModel(),
    toPlaces: () -> Unit
) {

    LaunchedEffect(Unit) {
        vm.openedScreen()
    }

    val state by vm.screenState.collectAsState()

    Scaffold(
        containerColor = state.weatherModel?.currentWeather?.weather?.weatherType?.backgroundColor() ?: Color.White,
        topBar = {
            WeatherTopAppBar(
                cityName = state.placeModel?.name ?: "",
                onClick = toPlaces
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = state.weatherModel?.currentWeather?.weather?.description ?: "")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = state.weatherModel?.currentWeather?.temperature?.toString() ?: "")
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = state.weatherModel?.currentWeather?.apparentTemperature?.toString() ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            ThreeHourWeatherBlock(hourlyForecast = state.weatherModel?.hourlyForecast ?: emptyList())
        }
    }
}

@Composable
fun ThreeHourWeatherBlock(
    hourlyForecast: List<ThreeHourWeatherModel>
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.Black.copy(alpha = 0.1f)
    ) {
        LazyRow {
            items(hourlyForecast) { threeHourModel ->
                ThreeHourItem(
                    time = threeHourModel.time,
                    weatherType = threeHourModel.weather.weatherType,
                    temperature = threeHourModel.temperature,
                    isDay = threeHourModel.partOfTheDay == PartOfTheDay.DAY
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun ThreeHourItem(
    time: LocalDateTime,
    weatherType: WeatherType,
    temperature: Double,
    isDay: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = time.format(DateTimeFormatter.ofPattern("HH:mm")))
        Spacer(modifier = Modifier.height(4.dp))
        Icon(
            painter = painterResource(id = weatherType.iconResId(
                isDay = isDay
            )),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = temperature.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(
    cityName: String,
    onClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = cityName,
                modifier = Modifier.clickable {
                    onClick()
                }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}


@Composable
fun WeatherType.iconResId(isDay:Boolean): Int {
    return when (this) {
        WeatherType.THUNDERSTORM -> R.drawable.ic_thunderstorm
        WeatherType.THUNDERSTORM_WITH_LIGHT_RAIN -> R.drawable.ic_thunderstorm_rain
        WeatherType.THUNDERSTORM_WITH_RAIN -> R.drawable.ic_thunderstorm_rain
        WeatherType.THUNDERSTORM_WITH_HEAVY_RAIN -> R.drawable.ic_thunderstorm_rain
        WeatherType.LIGHT_THUNDERSTORM ->  R.drawable.ic_thunderstorm
        WeatherType.HEAVY_THUNDERSTORM ->  R.drawable.ic_thunderstorm
        WeatherType.RAGGED_THUNDERSTORM ->  R.drawable.ic_thunderstorm
        WeatherType.THUNDERSTORM_WITH_LIGHT_DRIZZLE -> R.drawable.ic_thunderstorm_drizzle
        WeatherType.THUNDERSTORM_WITH_DRIZZLE -> R.drawable.ic_thunderstorm_drizzle
        WeatherType.THUNDERSTORM_WITH_HEAVY_DRIZZLE -> R.drawable.ic_thunderstorm_drizzle
        WeatherType.LIGHT_INTENSITY_DRIZZLE -> R.drawable.ic_drizzle
        WeatherType.DRIZZLE -> R.drawable.ic_drizzle
        WeatherType.HEAVY_INTENSITY_DRIZZLE -> R.drawable.ic_drizzle
        WeatherType.LIGHT_INTENSITY_DRIZZLE_RAIN -> R.drawable.ic_drizzle
        WeatherType.DRIZZLE_RAIN -> R.drawable.ic_drizzle
        WeatherType.HEAVY_INTENSITY_DRIZZLE_RAIN -> R.drawable.ic_drizzle
        WeatherType.SHOWER_RAIN_AND_DRIZZLE -> R.drawable.ic_drizzle
        WeatherType.HEAVY_SHOWER_RAIN_AND_DRIZZLE -> R.drawable.ic_drizzle
        WeatherType.SHOWER_DRIZZLE -> R.drawable.ic_drizzle
        WeatherType.LIGHT_RAIN -> R.drawable.ic_rain
        WeatherType.MODERATE_RAIN -> R.drawable.ic_rain
        WeatherType.HEAVY_INTENSITY_RAIN -> R.drawable.ic_rain
        WeatherType.VERY_HEAVY_RAIN -> R.drawable.ic_rain
        WeatherType.EXTREME_RAIN -> R.drawable.ic_rain
        WeatherType.FREEZING_RAIN -> R.drawable.ic_rain
        WeatherType.LIGHT_INTENSITY_SHOWER_RAIN -> R.drawable.ic_shower_rain
        WeatherType.SHOWER_RAIN -> R.drawable.ic_shower_rain
        WeatherType.HEAVY_INTENSITY_SHOWER_RAIN -> R.drawable.ic_shower_rain
        WeatherType.RAGGED_SHOWER_RAIN -> R.drawable.ic_shower_rain
        WeatherType.LIGHT_SNOW -> R.drawable.ic_snow
        WeatherType.SNOW -> R.drawable.ic_snow
        WeatherType.HEAVY_SNOW -> R.drawable.ic_snow
        WeatherType.SLEET -> R.drawable.ic_snow
        WeatherType.LIGHT_SHOWER_SLEET -> R.drawable.ic_snow
        WeatherType.SHOWER_SLEET -> R.drawable.ic_snow
        WeatherType.LIGHT_RAIN_AND_SNOW -> R.drawable.ic_snow
        WeatherType.RAIN_AND_SNOW -> R.drawable.ic_snow
        WeatherType.LIGHT_SHOWER_SNOW -> R.drawable.ic_snow
        WeatherType.SHOWER_SNOW -> R.drawable.ic_snow
        WeatherType.HEAVY_SHOWER_SNOW -> R.drawable.ic_snow
        WeatherType.MIST -> R.drawable.ic_fog
        WeatherType.SMOKE -> R.drawable.ic_fog
        WeatherType.HAZE -> R.drawable.ic_fog
        WeatherType.SAND_DUST_WHIRLS -> R.drawable.ic_windy
        WeatherType.FOG -> R.drawable.ic_fog
        WeatherType.SAND -> R.drawable.ic_fog
        WeatherType.DUST -> R.drawable.ic_fog
        WeatherType.VOLCANIC_ASH -> R.drawable.ic_fog
        WeatherType.SQUALLS -> R.drawable.ic_windy
        WeatherType.TORNADO -> R.drawable.ic_windy
        WeatherType.CLEAR_SKY -> if (isDay) R.drawable.ic_clear_sky_day else R.drawable.ic_clear_sky_night
        WeatherType.FEW_CLOUDS -> if (isDay) R.drawable.ic_few_clouds_sun else R.drawable.ic_few_clouds_night
        WeatherType.SCATTERED_CLOUDS -> if (isDay) R.drawable.ic_clouds_sun else R.drawable.ic_few_clouds_night
        WeatherType.BROKEN_CLOUDS -> if (isDay) R.drawable.ic_clouds_sun else R.drawable.ic_few_clouds_night
        WeatherType.OVERCAST_CLOUD -> R.drawable.ic_overcast
    }
}

@Composable
fun WeatherType.backgroundColor(): Color {
    return when(this) {
        WeatherType.THUNDERSTORM -> Color(0xFF2a3b4c)
        WeatherType.THUNDERSTORM_WITH_LIGHT_RAIN -> Color(0xFF2a3b4c)
        WeatherType.THUNDERSTORM_WITH_RAIN -> Color(0xFF2a3b4c)
        WeatherType.THUNDERSTORM_WITH_HEAVY_RAIN -> Color(0xFF2a3b4c)
        WeatherType.LIGHT_THUNDERSTORM -> Color(0xFF2a3b4c)
        WeatherType.HEAVY_THUNDERSTORM -> Color(0xFF2a3b4c)
        WeatherType.RAGGED_THUNDERSTORM -> Color(0xFF2a3b4c)
        WeatherType.THUNDERSTORM_WITH_LIGHT_DRIZZLE -> Color(0xFF2a3b4c)
        WeatherType.THUNDERSTORM_WITH_DRIZZLE -> Color(0xFF2a3b4c)
        WeatherType.THUNDERSTORM_WITH_HEAVY_DRIZZLE -> Color(0xFF2a3b4c)
        WeatherType.LIGHT_INTENSITY_DRIZZLE -> Color(0xFF5A8BBF)
        WeatherType.DRIZZLE -> Color(0xFF5A8BBF)
        WeatherType.HEAVY_INTENSITY_DRIZZLE -> Color(0xFF5A8BBF)
        WeatherType.LIGHT_INTENSITY_DRIZZLE_RAIN -> Color(0xFF5A8BBF)
        WeatherType.DRIZZLE_RAIN -> Color(0xFF5A8BBF)
        WeatherType.HEAVY_INTENSITY_DRIZZLE_RAIN -> Color(0xFF5A8BBF)
        WeatherType.SHOWER_RAIN_AND_DRIZZLE -> Color(0xFF5A8BBF)
        WeatherType.HEAVY_SHOWER_RAIN_AND_DRIZZLE -> Color(0xFF5A8BBF)
        WeatherType.SHOWER_DRIZZLE -> Color(0xFF5A8BBF)
        WeatherType.LIGHT_RAIN -> Color(0xFF516170)
        WeatherType.MODERATE_RAIN -> Color(0xFF516170)
        WeatherType.HEAVY_INTENSITY_RAIN -> Color(0xFF516170)
        WeatherType.VERY_HEAVY_RAIN -> Color(0xFF516170)
        WeatherType.EXTREME_RAIN -> Color(0xFF516170)
        WeatherType.FREEZING_RAIN -> Color(0xFF516170)
        WeatherType.LIGHT_INTENSITY_SHOWER_RAIN -> Color(0xFF516170)
        WeatherType.SHOWER_RAIN -> Color(0xFF516170)
        WeatherType.HEAVY_INTENSITY_SHOWER_RAIN -> Color(0xFF516170)
        WeatherType.RAGGED_SHOWER_RAIN -> Color(0xFF516170)
        WeatherType.LIGHT_SNOW -> Color(0xFFEEF4F6)
        WeatherType.SNOW -> Color(0xFFEEF4F6)
        WeatherType.HEAVY_SNOW -> Color(0xFFEEF4F6)
        WeatherType.SLEET -> Color(0xFFEEF4F6)
        WeatherType.LIGHT_SHOWER_SLEET -> Color(0xFFEEF4F6)
        WeatherType.SHOWER_SLEET -> Color(0xFFEEF4F6)
        WeatherType.LIGHT_RAIN_AND_SNOW -> Color(0xFFEEF4F6)
        WeatherType.RAIN_AND_SNOW -> Color(0xFFEEF4F6)
        WeatherType.LIGHT_SHOWER_SNOW -> Color(0xFFEEF4F6)
        WeatherType.SHOWER_SNOW -> Color(0xFFEEF4F6)
        WeatherType.HEAVY_SHOWER_SNOW -> Color(0xFFEEF4F6)


        WeatherType.MIST -> Color(0xFF8EA3A9)
        WeatherType.SMOKE -> Color(0xFF8EA3A9)
        WeatherType.HAZE -> Color(0xFF8EA3A9)
        WeatherType.FOG -> Color(0xFF8EA3A9)

        WeatherType.SAND_DUST_WHIRLS -> Color(0xFFF8E1B4)
        WeatherType.SAND -> Color(0xFFF8E1B4)
        WeatherType.DUST -> Color(0xFFF8E1B4)
        WeatherType.VOLCANIC_ASH -> Color(0xFFF8E1B4)


        WeatherType.SQUALLS -> Color(0xFF242424)
        WeatherType.TORNADO -> Color(0xFF242424)


        WeatherType.CLEAR_SKY -> Color(0xFFFFA600)


        WeatherType.FEW_CLOUDS -> Color(0xFF00A4E6)
        WeatherType.SCATTERED_CLOUDS ->Color(0xFF00A4E6)
        WeatherType.BROKEN_CLOUDS -> Color(0xFF00A4E6)
        WeatherType.OVERCAST_CLOUD -> Color(0xFF00A4E6)
    }
}


@Composable
fun WeatherType.textColor(): Color {
    return when(this) {
        WeatherType.THUNDERSTORM -> Color(0xFFf1f4f8)
        WeatherType.THUNDERSTORM_WITH_LIGHT_RAIN -> Color(0xFFf1f4f8)
        WeatherType.THUNDERSTORM_WITH_RAIN -> Color(0xFFf1f4f8)
        WeatherType.THUNDERSTORM_WITH_HEAVY_RAIN -> Color(0xFFf1f4f8)
        WeatherType.LIGHT_THUNDERSTORM -> Color(0xFFf1f4f8)
        WeatherType.HEAVY_THUNDERSTORM -> Color(0xFFf1f4f8)
        WeatherType.RAGGED_THUNDERSTORM -> Color(0xFFf1f4f8)
        WeatherType.THUNDERSTORM_WITH_LIGHT_DRIZZLE -> Color(0xFFf1f4f8)
        WeatherType.THUNDERSTORM_WITH_DRIZZLE -> Color(0xFFf1f4f8)
        WeatherType.THUNDERSTORM_WITH_HEAVY_DRIZZLE -> Color(0xFFf1f4f8)
        WeatherType.LIGHT_INTENSITY_DRIZZLE -> Color(0xFFF0F6FF)
        WeatherType.DRIZZLE -> Color(0xFFF0F6FF)
        WeatherType.HEAVY_INTENSITY_DRIZZLE -> Color(0xFFF0F6FF)
        WeatherType.LIGHT_INTENSITY_DRIZZLE_RAIN -> Color(0xFFF0F6FF)
        WeatherType.DRIZZLE_RAIN -> Color(0xFFF0F6FF)
        WeatherType.HEAVY_INTENSITY_DRIZZLE_RAIN -> Color(0xFFF0F6FF)
        WeatherType.SHOWER_RAIN_AND_DRIZZLE -> Color(0xFFF0F6FF)
        WeatherType.HEAVY_SHOWER_RAIN_AND_DRIZZLE -> Color(0xFFF0F6FF)
        WeatherType.SHOWER_DRIZZLE -> Color(0xFFF0F6FF)
        WeatherType.LIGHT_RAIN ->  Color(0xFFD8EAF3)
        WeatherType.MODERATE_RAIN -> Color(0xFFD8EAF3)
        WeatherType.HEAVY_INTENSITY_RAIN -> Color(0xFFD8EAF3)
        WeatherType.VERY_HEAVY_RAIN -> Color(0xFFD8EAF3)
        WeatherType.EXTREME_RAIN -> Color(0xFFD8EAF3)
        WeatherType.FREEZING_RAIN -> Color(0xFFD8EAF3)
        WeatherType.LIGHT_INTENSITY_SHOWER_RAIN -> Color(0xFFD8EAF3)
        WeatherType.SHOWER_RAIN -> Color(0xFFD8EAF3)
        WeatherType.HEAVY_INTENSITY_SHOWER_RAIN -> Color(0xFFD8EAF3)
        WeatherType.RAGGED_SHOWER_RAIN -> Color(0xFFD8EAF3)
        WeatherType.LIGHT_SNOW -> Color(0xFF5794BC)
        WeatherType.SNOW -> Color(0xFF5794BC)
        WeatherType.HEAVY_SNOW -> Color(0xFF5794BC)
        WeatherType.SLEET -> Color(0xFF5794BC)
        WeatherType.LIGHT_SHOWER_SLEET -> Color(0xFF5794BC)
        WeatherType.SHOWER_SLEET -> Color(0xFF5794BC)
        WeatherType.LIGHT_RAIN_AND_SNOW -> Color(0xFF5794BC)
        WeatherType.RAIN_AND_SNOW -> Color(0xFF5794BC)
        WeatherType.LIGHT_SHOWER_SNOW -> Color(0xFF5794BC)
        WeatherType.SHOWER_SNOW -> Color(0xFF5794BC)
        WeatherType.HEAVY_SHOWER_SNOW -> Color(0xFF5794BC)


        WeatherType.MIST -> Color(0xFFF8FAFC)
        WeatherType.SMOKE -> Color(0xFFF8FAFC)
        WeatherType.HAZE -> Color(0xFFF8FAFC)
        WeatherType.FOG -> Color(0xFFF8FAFC)

        WeatherType.SAND_DUST_WHIRLS -> Color(0xFF51493E)
        WeatherType.SAND -> Color(0xFF51493E)
        WeatherType.DUST -> Color(0xFF51493E)
        WeatherType.VOLCANIC_ASH -> Color(0xFF51493E)


        WeatherType.SQUALLS -> Color(0xFFFFFFFF)
        WeatherType.TORNADO -> Color(0xFFFFFFFF)


        WeatherType.CLEAR_SKY -> Color(0xFFF9F4B8)


        WeatherType.FEW_CLOUDS -> Color(0xFFF7F8FD)
        WeatherType.SCATTERED_CLOUDS -> Color(0xFFF7F8FD)
        WeatherType.BROKEN_CLOUDS -> Color(0xFFF7F8FD)
        WeatherType.OVERCAST_CLOUD -> Color(0xFFF7F8FD)
    }
}
