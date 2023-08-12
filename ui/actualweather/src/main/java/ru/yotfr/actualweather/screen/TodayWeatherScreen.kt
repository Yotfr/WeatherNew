package ru.yotfr.actualweather.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yotfr.actualweather.viewmodel.TodayWeatherViewModel
import ru.yotfr.shared.R
import ru.yotfr.shared.model.HourlyModel
import ru.yotfr.shared.model.WeatherType
import java.time.format.DateTimeFormatter
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayWeatherScreen(
    vm: TodayWeatherViewModel,
    navigateToWeeklyForecast: () -> Unit
) {
    val screenState by vm.screenState.collectAsState()

    LaunchedEffect(Unit) {
        vm.openedScreen()
    }

    Scaffold(
        topBar = {
            WeatherTopAppBar(
                placeName = "Location",
                onPlacesClicked = { },
                onSettingsClicked = { }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = screenState.selectedWeatherModel?.temperature.toTemperature(),
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.feels_like) + " " + screenState.selectedWeatherModel?.apparentTemperature.toTemperature(),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                onClick = navigateToWeeklyForecast,
                modifier = Modifier.align(Alignment.End)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.weekly_forecast),
                        fontSize = 60.sp,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            LazyRow {
                items(screenState.hourlyForecast) {
                    WeatherHourlyItem(hourlyModel = it)
                }
            }
        }
    }
}

@Composable
fun WeatherHourlyItem(
    hourlyModel: HourlyModel
) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text = hourlyModel.time.format(
                DateTimeFormatter.ofPattern(
                    "HH:mm",
                    Locale.getDefault()
                )
            ),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Icon(
            painter = hourlyModel.weatherType.icon(isDay = hourlyModel.isDay),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = hourlyModel.weatherType.stringRes(),
            color = Color.White
        )
    }
}

@Composable
fun WeatherType.icon(isDay: Boolean): Painter {
    return when (this) {
        WeatherType.CLEAR_SKY -> if (isDay) painterResource(R.drawable.ic_clear_sky_day) else
            painterResource(R.drawable.ic_clear_sky_night)

        WeatherType.MAINLY_CLEAR -> if (isDay) painterResource(R.drawable.ic_almost_clear_sky_day) else
            painterResource(R.drawable.ic_almost_clear_sky_night)

        WeatherType.PARTLY_CLOUDY -> if (isDay) painterResource(R.drawable.ic_cloudy_day) else
            painterResource(R.drawable.ic_cloudy_night)

        WeatherType.OVERCAST -> painterResource(R.drawable.ic_overcast)
        WeatherType.FOG -> painterResource(R.drawable.ic_fog)
        WeatherType.DRIZZLE -> painterResource(R.drawable.ic_rain)
        WeatherType.FREEZING_DRIZZLE -> painterResource(R.drawable.ic_rain)
        WeatherType.RAIN -> painterResource(R.drawable.ic_rain)
        WeatherType.FREEZING_RAIN -> painterResource(R.drawable.ic_rain)
        WeatherType.SNOWFALL -> painterResource(R.drawable.ic_snow)
        WeatherType.SNOW_GRAINS -> painterResource(R.drawable.ic_snow)
        WeatherType.RAIN_SHOWER -> painterResource(R.drawable.ic_rainshower)
        WeatherType.SNOW_SHOWER -> painterResource(R.drawable.ic_snowshower)
        WeatherType.THUNDERSTORM -> painterResource(R.drawable.ic_hail)
        WeatherType.THUNDERSTORM_WITH_HAIL -> painterResource(R.drawable.ic_hail)
        WeatherType.UNKNOWN -> painterResource(R.drawable.ic_fog)
    }
}

@Composable
fun WeatherType.stringRes(): String {
    return when (this) {
        WeatherType.CLEAR_SKY -> stringResource(R.string.clear_sky)
        WeatherType.MAINLY_CLEAR -> stringResource(R.string.almost_clear)
        WeatherType.PARTLY_CLOUDY -> stringResource(R.string.cloudy)
        WeatherType.OVERCAST -> stringResource(R.string.overcast)
        WeatherType.FOG -> stringResource(R.string.fog)
        WeatherType.DRIZZLE -> stringResource(R.string.drizzle)
        WeatherType.FREEZING_DRIZZLE -> stringResource(R.string.drizzle)
        WeatherType.RAIN -> stringResource(R.string.rain)
        WeatherType.FREEZING_RAIN -> stringResource(R.string.rain)
        WeatherType.SNOWFALL -> stringResource(R.string.snowfall)
        WeatherType.SNOW_GRAINS -> stringResource(R.string.snow_grains)
        WeatherType.RAIN_SHOWER -> stringResource(R.string.rainshower)
        WeatherType.SNOW_SHOWER -> stringResource(R.string.snowshower)
        WeatherType.THUNDERSTORM -> stringResource(R.string.thunderstorm)
        WeatherType.THUNDERSTORM_WITH_HAIL -> stringResource(R.string.thunderstorm_hail)
        WeatherType.UNKNOWN -> stringResource(R.string.unknown)
    }
}

fun Int?.toTemperature(): String {
    return if (this == null) "" else "$this \u2103"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(
    placeName: String,
    onPlacesClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = placeName) },
        navigationIcon = {
            IconButton(
                onClick = onPlacesClicked
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = onSettingsClicked
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null
                )
            }
        }
    )
}