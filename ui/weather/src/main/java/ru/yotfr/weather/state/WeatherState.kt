package ru.yotfr.weather.state

import ru.yotfr.shared.model.FullWeatherModel
import ru.yotfr.shared.model.PlaceModel
import ru.yotfr.shared.redux.State
data class WeatherState(
    val showProgressBar: Boolean = false,
    val weatherModel: FullWeatherModel? = null,
    val placeModel: PlaceModel? = null,
    val errorMessage: String = ""
) : State