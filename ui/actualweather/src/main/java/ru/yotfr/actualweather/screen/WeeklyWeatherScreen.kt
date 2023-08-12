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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yotfr.actualweather.viewmodel.WeeklyWeatherViewModel
import ru.yotfr.shared.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeeklyWeatherScreen(
    vm: WeeklyWeatherViewModel,
    navigateBack: () -> Unit
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
                text = screenState.selectedWeatherModel?.maxTemperature.toTemperature(),
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.feels_like) + " " + screenState.selectedWeatherModel?.minTemperature.toTemperature(),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                onClick = navigateBack,
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
            }
        }
    }
}